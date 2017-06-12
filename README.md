# NaiveBayes-ComputationalLinguistics

CSE 467/567 Project: Using Naive Bayes on movie subtitles from a variety of languages from a large corpus. Applies Naive Bayes to accurately predict what language a given text is using character n grams. 

# Running the project:

You will want to modify your bashrc PATH by appending:

# /corpora/local/bin/scala 

This saves from further typing of the entire path of the file to your program. 

We also ran our programs on remote UB servers. In any case, to compile use, 

# sbt package on the command line. You may have to reload your bashrc. Reload with Source ~/.bashrc command should work.

To produce an output for the character 'n' gram, do:

# scala -cp path_to_jar lin567_p1.Run train.labeled dev.labeled L N

replace L with the lambda smoothing parameter and N as the specified n-gram length. For example, a model trigram or 3 - gram with a lambda smoothing parameter of 1 would be:

# scala -cp path_to_jar lin567_p1.Run train.labeled dev.labeled 1 3

We also had a perl script that was prepared to us for evaluation in evaluate.p1. To evaluate our models, we use:

# scala -cp path_to_jar lin567_p1.Run train.labeled dev.labeled 3 1 | ./evaluate.pl –gold dev.labeled

replace path_to_jar in the command line with the actual path to the jar file in your project directory.

The script itself provides classification accuracy across all languages and for each individual language.
