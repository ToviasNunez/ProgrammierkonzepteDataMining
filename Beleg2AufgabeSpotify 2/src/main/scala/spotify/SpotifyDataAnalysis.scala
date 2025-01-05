package spotify

import spotify.models.Song

object SpotifyDataAnalysis {

  def getSongWithMostStreams(l:List[Song]):Song=
    /*
      The functions gets the most streamed song
      Use only an aggregation function
     */
      //l.map(song => (song, song.streams)).reduce((a,b) => if(a._2 > b._2) a else b)._1
//      l.filter(song => song.streams == l.map(_.streams).max)
//        .reduce((song1, song2) => song1)
        l.maxBy(_.streams)

  def getNameAndNumberOfTheArtistWithMostSongsInList(l:List[Song]):(String,Int)=
    /*
      Get the Name of the Artist with the most Songs in the streaming list
      and the number of occurences
     */
//     l.map(_.artist)// extract artis name
//       .groupBy(identity) // group by artis name
//       .map{case (artist, song) => (artist , song.size)} // Map to (artist , count)
//       .reduce((a,b) => if (a._2 > b._2) a else b)
    l.groupBy(_.artist) // Group by artist name
      .map { case (artist, songs) => (artist, songs.size) } // Map to (artist, number of songs)
      .maxBy(_._2) // Find the artist with the most songs

  def getArtistWithMostStreams(l: List[Song]): (String, BigInt) =

    /*
       Gets the Artist with the most streams in total
       and the number of streams
     */
//      l.map(song => (song.artist , song.streams)) // Map to (artist , streams)
//        .groupBy(_._1) // group by artist name
//        .map {case (artist , stream) => (artist , stream.map(_._2).sum)} // Sum stream
//        .reduce((a,b) => if (a._2 > b._2) a else b) // reduce to fin the artis with the most streams
      l.groupBy(_.artist) // Group by artist name
        .map { case (artist, songs) => (artist, songs.map(_.streams).sum) } // Map to (artist, total streams)
        .maxBy(_._2) // Find the artist with the most streams

  def getMinAndMaxAndAvgBPM(l:List[Song]):(Int,Int,Double)=

    /*
      Gets the minimum, maximum and avg BPM of all songs
      Use only one aggregation function to calculate the values
     */
      l.map(_.bpm) // extract the MPB values form the list of the song
        .foldLeft(Int.MaxValue , Int.MinValue , 0) // initialize the accumulation
        {case ((min,max, sum) , bpm) =>
          //Update the accumulation
          //Calculate the new Minimum
          // Calculate the nre max
          //add the current bpm to the running  total
          (min.min(bpm) , max.max(bpm) , sum + bpm)}
      //after folding, match the result tuple to extract the values
         match{case (min, max, sum) =>
        // calculate the average BPM by dividing the sum by number of the sum
        (min , max, sum.toDouble / l.size)} // calculate the average

  def getThe4MonthWithMostMinorSongs(l:List[Song]):List[(Int,Double)]=
    /*
     Gets the 4 months (release date) with the most songs written in minor
     (use the relative values not the absolute)
    */
    // Filter minor songs
    l.filter(_.mode.toLowerCase == "minor")
      .groupBy(_.released_month) // Group by month
      .map { case (month, songs) =>
        // Calculate relative percentage: minor songs in the month / total songs in the month
        val totalSongsInMonth = l.count(_.released_month == month)
        val percentage = songs.size.toDouble / totalSongsInMonth
        (month, percentage)
      }
      .toList // Convert to a list
      .sortBy(-_._2) // Sort by percentage in descending order
      .take(4) // Take the top 4 months



  def getWords(line: String): List[String] =
    /*
     * Extracts all words from a line
     *
     * 1. Removes all characters which are not letters (A-Z or a-z)
     * 2. Shifts all words to lower case
     * 3. Extracts all words and put them into a list of strings
     */
      line // remove non-letter characters by replacing them with spaces
        .map(c => if (c.isLetter) c else ' ')
      // convert all characters to lowercase
        .map(_.toLower)
      // split by whitespaces into word and filter out empty string
        .split("\\s+") // splits the string into words, using one or more spaces as the delimiter.
        .toList
        .filter(_.nonEmpty) // ensures only non-empty words are retained.


  def getAllWords(l: List[Song]): List[String] =
    /*
     * Extracts all words from a List of Songs
     * The words should be in the same order as they occur in the source document
     *
     * Hint: Use the flatMap function
     */
      l.flatMap(song => song.track
        .replaceAll("[^A-Za-z]", " ")
        .toLowerCase()
        .split("\\s+")
        .filter(_.nonEmpty)
        .toList
      )


  def getThe4MostFrequentWordsInTitle(l:List[Song]):List[(String,Int)]=
    /*
      Gets the 4 most frequent words that occur in the titles of a songlist.
     */
    l.flatMap(_.track.toLowerCase.replaceAll("[^a-z]", " ").split("\\s+").filter(_.nonEmpty))
       .groupBy(identity)
       .view.mapValues(_.size)
       .toMap
       .toList
       .sortBy(-_._2)
       .take(4)


  def getThe20MostFrequentWordsInTitleWithFilter(l:List[Song], predicate: String=>Boolean):List[(String,Int)]=
    /*
      Get the 20 most frequent words occurring in the titles of a songlist
      Integrate a filter predicate that decides whether a word is in the resulting list or not.
      Give back tuples with the word and the number of occurrences.
     */

    l.flatMap(_.track.toLowerCase.replaceAll("[^a-z]", " ").split("\\s+").filter(word => word.nonEmpty && predicate(word)))
       .groupBy(identity)
       .view.mapValues(_.size)
       .toMap
       .toList
       .sortBy(-_._2)
       .take(20)

  def getAllWordsWithIndex(l:List[Song]):Set[(Long,String)] =
    /*
      Êxtract all words of the titles of a songlist and give back tuples containing
      the ID of the song and the word
     */
    l.flatMap(song =>
      song.track.toLowerCase
        .replaceAll("[^a-z]", " ") // Remove non-alphabetic characters
        .split("\\s+")             // Split into words
        .filter(_.nonEmpty)        // Remove empty strings
        .map(word => (song.id, word)) // Pair the word with the song ID
    ).toSet // Convert to a set to ensure unique pairs

  def createInverseIndex(wwi:Set[(Long, String)]):Map[String,Set[Long]]=

    /*
      Create an inverse index consisting of all words as the key element and
      the IDs of all songs that contains the certain word as the value.
      The function gets all words with the index (see function above)
      and returns a map.
     */
    wwi.groupBy(_._2) // Group by the word (second element of the tuple)
      .view.mapValues(_.map(_._1).toSet) // Map each word to the set of IDs
      .toMap // Convert the view back to a strict Map

  def orConjunction(words: List[String], invInd: Map[String, Set[Long]]): Set[Long] =
    /*
    * The Functions gets a list of words and returns a set of tweet ids where at least one
    * of the word occurs
    * Use the inverse index for calculating the or-Operation.
     */
    words.flatMap(invInd.getOrElse(_, Set.empty)).toSet

  def andConjunction(words: List[String], invInd: Map[String, Set[Long]]): Set[Long] =

    /*
   * The Functions gets a list of words and returns a set of tweet ids where all of the words occur
  * Use the inverse index for calculating the and-Operation.
    */
    words.map(word => invInd.getOrElse(word, Set.empty[Long])) // Get the set of song IDs for each word
      .reduceOption((a, b) => a.intersect(b)) // Perform intersection to get common IDs
      .getOrElse(Set()) // If no common IDs, return an empty set

  def findSongsWithAtLeast2WordsFromWordlist(l:List[Song], words:List[String]):Set[(Long,String,Set[String])]=

    /*
      Find all songs with at least two words of the wordlist. Return triples containing the ID of the song, the title and
      the words that occur in the title.
    */
    l.flatMap(song => {
      val titleWords = song.track
        .replaceAll("[^A-Za-z]", " ")
        .toLowerCase
        .split("\\s+")
        .toSet
        .intersect(words.map(_.toLowerCase).toSet)

      if (titleWords.size >= 2) Some((song.id, song.track, titleWords)) else None
    }).toSet


    /*

    Write three own functions that analysis the dataset! Write the functions with some explanations and corresponding tests!

     */


  def getTopSongsByAttributes(songs: List[Song]): List[(String, Int, String, String)] = {
    val attributes = List(
      ("danceability", (s: Song) => s.danceability),
      ("valence", (s: Song) => s.valence),
      ("energy", (s: Song) => s.energy),
      ("acousticness", (s: Song) => s.acousticness),
      ("instrumentalness", (s: Song) => s.instrumentalness),
      ("liveness", (s: Song) => s.liveness),
      ("speechiness", (s: Song) => s.speechiness)
    )

    attributes.map { case (attributeName, attributeFunc) =>
      val topSong = songs.maxBy(attributeFunc)
      (attributeName, topSong.released_year, topSong.track, topSong.artist)
    }
  }

  def getMostStreamedSongPlatformAndArtistByYear(songs: List[Song]): Map[Int, (String, String , String)] = {
    songs.groupBy(_.released_year).map { case (year, songsInYear) =>
      val mostStreamedSong = songsInYear.maxBy(_.streams)
      val platform = if (mostStreamedSong.in_spotify_charts > 0) "Spotify"
      else if (mostStreamedSong.in_apple_charts > 0) "Apple Music"
      else if (mostStreamedSong.in_deezer_charts > 0) "Deezer"
      else if (mostStreamedSong.in_shazam_charts > 0) "Shazam"
      else "Unknown"
      year -> (platform, mostStreamedSong.artist , mostStreamedSong.track)
    }
  }

  def getPlatformRankingByStreams(songs: List[Song]): List[(String, BigInt)] = {
    val platformStreams = songs.groupBy { song =>
      if (song.in_spotify_charts > 0) "Spotify"
      else if (song.in_apple_charts > 0) "Apple Music"
      else if (song.in_deezer_charts > 0) "Deezer"
      else if (song.in_shazam_charts > 0) "Shazam"
      else "Unknown"
    }.view.mapValues(_.map(_.streams).sum).toMap
    platformStreams.toList.sortBy(-_._2)
  }


}


