package lin567_p1


class NaiveBayes {
  // Use these to compute P( Language )
  var docLanguageCounts = Map[Language,Double]().withDefaultValue(0D)
  var docCount = 0D
  var totalVocabulary = Set[String]()
  var totalUniqueNGrams = 0D
  //var UniqueNGramKey = Map[String,Double]().withDefaultValue(0D)
  
  //var documentMap = Map[Language,String]().withDefaultValue(0D)
  // Use these to compute P( Word | Language )
  var languageWordCounts = Map[Tuple2[Language,String],Double]().withDefaultValue(0D)

  // This should increment counts so you can compute P( Language ) and P( Word | Language )
  def train( corpus:Set[Document] ) {
    // This loops over the set of documents, and provides variables for the document id as a String,
    // the document text as an Array[String], and the language as a Language
    corpus.foreach{ case Document( id, text, language ) =>
      // Implement me

	docCount += 1
	docLanguageCounts = docLanguageCounts.updated(language, docLanguageCounts(language) + 1)
	//this should loop and for each document in the corpus add a count for each occurence of a language

	println(language)
	println(docLanguageCounts)
	//println(p_Lg(language))

	text.foreach{ characterNGrams => languageWordCounts += (language, characterNGrams) -> ( languageWordCounts((language,characterNGrams)) +1)
        //UniqueNGramKey += (characterNGrams) -> (UniqueNGramKey(characterNGrams)) +1
	//languageWordCounts += totalNGramKey -> (languageWordCounts( totalNGramKey) + 1 )
	//println(totalNGramKey)
	totalVocabulary += characterNGrams
	}

	//doesn't store 2 things?
	//above prints out total repeating set of possible characters
	//println(p_wordGivenLg("o", language, 9))
	/*
	languageWordCounts.keys.foreach{ case (language, _ ) => 
		if(language == Language("")){
			totalUniqueNGrams +=1
		}
	}
	*/

	//println(p_docAndLg(text,language,3))
	//println(totalUniqueNGrams)
	//println(totalVocabulary)
	//println(totalUniqueNGrams)	
	//println(languageWordCounts)
	println(docCount)
	}	
  }

  // Should compute P( word | language ). Implement with add-lambda smoothing.
  def p_wordGivenLg( word:String, language:Language, lambda:Double ) = {
    // IMPLEMENT ME
	//P(cgram = "vla" | language = cze) = #(vla,cze) / #(   , cze)
  	(languageWordCounts((language,word)) + lambda) / (languageWordCounts(language, word) + lambda * (totalVocabulary.size +1))
	
  }

  // Should compute P( Language )
  def p_Lg( language:Language ) = {
    // IMPLEMENT ME
	//P(lg = cze) = #(cze docs) / #docs
	docLanguageCounts(language) / docCount
  }


  // Should compute P( Doc, Language )= P( Language )\prod_{Word in Document}P( Word | Language )
  def p_docAndLg( document:Array[String], language:Language, lambda:Double ) = {
    // IMPLEMENT ME
	//
	//document.foreach{ cNGrams => documentMap(language) -> (documentMap(language) + cNGrams)
	//document.foreach{ p_wordGivenLg(documentMap(language),language,lambda) * p_Lg(language)
	document.foreach {  cNGrams => p_wordGivenLg(cNGrams,language,lambda) * p_Lg(language)
	}
	//}
	//}
	//put the cNGrams into a map and add it in otherwise
  }


  // This function takes a document as a parameter, and returns the highest scoring language as a
  // Language object. 
  def mostLikelyLanguage( document:Array[String], lambda:Double ) = {
    // Loop over the possible languages (they should accessible in docLanguageCounts.keys), and find
    // the language with the highest P( Document, Language ) score
	var Max = 0D
	var counter = 0D
	var Key = Language("")
	
	docLanguageCounts.keys.foreach{ language => counter = p_docAndLg(document,language,lambda)	
	// counter would be Probability(Doc,Language) for that language in the map
	
	//	println(language)
	//	println(counter)
		if(counter >= Max){
		Max = counter
		Key = language
		}
	//	Language((Key.toString()))
		
	}
	    Language((Key.toString()))
  }
}
