package titanic

object NaiveBayes {

  /**
   * Counts the number of distinct attribute values for each attribute
   * given in the attribute list
   *
   * @param data    Data Set for counting
   * @param attribList List of the attributes that should be counted
   * @return A Map with the attribute name as the key and the number of distinct
   *         values as the value
   */
  def countAttributeValues(data:List[Map[String, Any]], attribList:String): Map[Any,Int]=
    data.groupBy(_(attribList)).view.mapValues(_.size).toMap

  /**
   * Extracts all attribute names that occur in a data set
   *
   * @param data    Data Set to be searched
   * @return A List of the attribute names that appear in the data set
   */
  def getAttributes(data:List[Map[String, Any]]):Set[String]= data.flatMap(_.keys).toSet

  /**
   * Extracts all attribute values that occur in a data set.
   *
   * @param data    Data Set to be extracted
   * @return A Map that consists of all attributes and their corresponding attribute values.
   *         The attribute values are stored in a Set.
   */
  def getAttributeValues(data:List[Map[String, Any]]):Map[String,Set[Any]]={

    val attribs= getAttributes(data)
    attribs.map(a => (a,data.map(_(a)).groupBy(identity).keys.toSet)).
      toMap
  }

  /**
   * Calculate the prior propabilities of each class.
   *
   * @param data    Data Set to be used for calculation
   * @param classAttrib Name of the attribute that contains the class assignment
   * @return A Map that consists of all classes (as key) and their corresponding prior propabilities.
   *
   */
  def calcPriorPropabilities(data:List[Map[String, Any]], classAttrib:String):Map[Any,Double]=
    countAttributeValues(data,classAttrib).view.mapValues(_.toDouble/data.size).toMap

  /**
   * This function should count for each class and attribute how often an
   * attribute value occurs in the data set. The result should be a Map that consists of
   * a class as the key element. The value of the Map should be a Set of 2-tuples where
   * the first element is the attribute name and the second element is a map. This map
   * stores the attribute value as the key and the number of occurrences as the value (see
   * test for further details
   *
   * @param data    Data Set to be used for calculation
   * @param classAttrib Name of the attribute that contains the class assignment
   * @return A Map that consists of all classes (as key) and a set of tuples (as value)
   *         that contains all attributes with their name (first element) and the corresponding
   *         number of occurrences stored in a Map(second element).
   *
   */
  def calcAttribValuesForEachClass(data:List[Map[String, Any]], classAttrib:String):
  Map[Any, Set[(String, Map[Any, Int])]] = {
    data.groupBy(_(classAttrib)).view.mapValues { records =>
      val attributes = getAttributes(data) - classAttrib
      attributes.map { att =>
        val valueCounts = records.groupBy(_(att)).view.mapValues(_.size).toMap
        (att, valueCounts)
      }.toSet
    }.toMap
  }
  /**
   * This function should calculate the conditional propabilities for each class and attribute.
   * It takes the number of occurences of each attribute value for each class
   * (result of calcAttribValuesForEachClass) and the number of occurences of
   * each class in the training data set.
   * During caluculation it divides the number of occurences of each attribute value by
   * the number of class occurences.
   *
   * @param data    Attribute counts for each class and attribute calculated
   *                by calcAttribValuesForEachClass
   * @param classCounts Number of occurences of each class in the training data set
   * @return A Map that consists of all classes (as key) and a set of tuples (as value)
   *         that contains all attributes with their name (first element) and the corresponding
   *         conditional propability stored in a Map(second element).
   */
  def calcConditionalPropabilitiesForEachClass(data: Map[Any, Set[(String, Map[Any, Int])]],classCounts:Map[Any,Int]):
  Map[Any,Set[(String, Map[Any, Double])]] = {
      data.map{
        case( classKey, attrSet) =>
          val total = classCounts(classKey).toDouble
          classKey -> attrSet.map {
            case (att, valueCounts) =>
              (att,valueCounts.map { case(value,count) => value -> (count.toDouble/total)
              })
          }
      }
  }

  /**
   * This function should calculate the class propability values for each class.
   * It takes a data record that should be classified. Furthermore it takes the
   * conditional propabilties (calculated by calcConditionalPropabilitiesForEachClass)
   * and the prior propabilities (calculated by calcPriorPropabilities).
   * It multiplies the corresponding conditional propabilities and the prior propability of
   * the class.
   * The result of the function is the Naive Bayes Propability for each class.
   *
   * @param record  Data record that should be classified
   * @param conditionalProps Conditional propabilities
   * @param priorProps Prior Propabilities
   * @return A Map that consists of all classes (as key) and their corresponding propability
   */
  def calcClassValuesForPrediction(record:Map[String,Any], conditionalProps: Map[Any,Set[(String, Map[Any, Double])]],
                                   priorProps:Map[Any,Double]):Map[Any,Double]= {
    conditionalProps.map {
      case (classKey, attrSet) =>
        classKey -> attrSet.foldLeft(1.0) {
          case (acc, (att, valueCounts)) =>
            val value = record(att)
            valueCounts.getOrElse(value, 0.0) * acc
        } * priorProps(classKey)
    }
  }
  /**
   * This function finds the class with the highest propability
   *
   * @param classProps  Map that contains the class (key) and the corresponding propability
   * @return The class wit the highest propability
   */
  def findBestFittingClass(classProps:Map[Any,Double]):Any= classProps.maxBy(_._2)._1

  /**
   * This function builds the model. It is represented as a function that maps a data record
   * and the name of the id-attribute to the value of the id attribute and the predicted class
   *
   * @param trainDataSet  Training Data Set
   * @param classAttrib name of the attribute that contains the class
   * @return A tuple consisting of the id (first element) and the predicted class (second element)
   */
  def modelForTrainExample(trainDataSet: List[Map[String, Any]], classAttrib:String):
  (Map[String, Any], String) => (Any, Any) ={

    val classVals= NaiveBayes.countAttributeValues(trainDataSet,classAttrib)
    val data= NaiveBayes.calcAttribValuesForEachClass(trainDataSet,classAttrib)
    val condProp = NaiveBayes.calcConditionalPropabilitiesForEachClass(data,classVals)
    val prior= NaiveBayes.calcPriorPropabilities(trainDataSet,classAttrib)
    (map,id_key) => (map(id_key),findBestFittingClass(NaiveBayes.calcClassValuesForPrediction(map-id_key,condProp,prior)))
  }

  /**
   * This function applies the model to a data set.
   *
   * @param model  Machine Learning Model
   * @param testdata Set of data records where the class should be predicted
   * @return A set/sequence of predictions represented by tuples consisting
   *         of the id (first element) and the predicted class (second element)
   */
  def applyModel[CLASS, ID](model: (Map[String, Any], String) => (ID, CLASS),
                            testdata: Seq[Map[String, Any]], idKey: String): Seq[(ID, CLASS)] = {

    testdata.map(d => model(d, idKey))
  }

  /**
   * This function should calculate the conditional propabilities for each class and attribute.
   * Does the same as calcConditionalPropabilitiesForEachClass. The difference is that it should
   * use the add one smoothing technique described in the lecture
   * It takes an additional parameter - the attribute values for each attribute. This is necessary,
   * when an attribute value does not appear in a certain class.
   *
   * @param data    Attribute counts for each class and attribute calculated
   *                by calcAttribValuesForEachClass
   * @param attValues Contains the occuring attribute values for each attribute
   * @param classCounts Number of occurences of each class in the training data set
   * @return A Map that consists of all classes (as key) and a set of tuples (as value)
   *         that contains all attributes with their name (first element) and the corresponding
   *         conditional propability stored in a map(second element).
   */
  def calcConditionalPropabilitiesForEachClassWithSmoothing(
  data: Map[Any, Set[(String, Map[Any, Int])]], 
  attValues: Map[String, Set[Any]], 
  classCounts: Map[Any, Int]
): Map[Any, Set[(String, Map[Any, Double])]] = {

  data.map { case (classKey, attrSet) =>
    val total = classCounts(classKey).toDouble
    classKey -> attrSet.map { case (att, valueCounts) =>
      val valueCount = valueCounts.map { case (value, count) => value -> ((count + 1).toDouble / (total + attValues(att).size)) }
      val missingValues = attValues(att) -- valueCount.keys
      val smoothedValueCounts = missingValues.foldLeft(valueCount) { case (acc, value) =>
        acc + (value -> (1.0 / (total + attValues(att).size)))
      }
      (att, smoothedValueCounts)
    }
  }
}

  /**
   * This function builds the model using add one smoothing.
   * It is represented as a function that maps a data record
   * and the name of the id-attribute to the value of the id attribute and the predicted class
   *
   * @param trainDataSet  Training Data Set
   * @param classAttrib name of the attribute that contains the class
   * @return A tuple consisting of the id (first element) and the predicted class (second element)
   */

  def modelwithAddOneSmoothing(trainDataSet: List[Map[String, Any]], classAttrib:String):
  (Map[String, Any], String) => (Any, Any) ={

    val classVals= NaiveBayes.countAttributeValues(trainDataSet,classAttrib)
    val aValues = getAttributeValues(trainDataSet).asInstanceOf[ Map[String, Set[Any]]]
    val data: Map[Any, Set[(String, Map[Any, Int])]] = NaiveBayes.calcAttribValuesForEachClass(trainDataSet,classAttrib)
    val condProp =calcConditionalPropabilitiesForEachClassWithSmoothing(data,aValues,classVals)
    val prior= NaiveBayes.calcPriorPropabilities(trainDataSet,classAttrib)
    (map,id_key) => (map(id_key),findBestFittingClass(NaiveBayes.calcClassValuesForPrediction(map-id_key,condProp,prior)))
  }

  /*****************************************************************************
   *                            Utils                                          *
   *   helper Functions used by the tests                                      *
   *****************************************************************************/

  def extractValues(data: Map[Any, Set[(String, Map[Any, Any])]]):Set[(Any,Any,Any)]={

    val x= for ((key,classVals) <- data ; (att,attVals) <-classVals ; (k,v)<-attVals) yield(
      key,k,v
    )
    x.toSet
  }
}