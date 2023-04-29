# Map Reduce on DBLP data

## Description: Design and implement an instance of the Hadoop MapReduce computational model to perform analyses on DBLP publication data, mainly computing the number of articles published in each journal per year.

This project implements the Hadoop MapReduce computational model to perform analyses on DBLP publication data, specifically computing the number of articles published in each journal per year. This can provide valuable insights into trends and patterns in academic publishing.

The MapReduce process involves mapping input data into key-value pairs, then reducing them into a set of aggregated values. In this project, we parse the DBLP data and map the relevant fields (journal name and publication year) into key-value pairs. The reduce function then aggregates the counts of articles published in each journal for each year.

# Requirements

To run this project, you will need:

* Hadoop
* Java

# How it Works
To parse the DBLP XML data into blocks of `<article>` and `</article>`, we use Apache Mahout's XML Parser library. Each block is then sent to a mapper, which extracts the relevant fields (journal name and publication year) from the XML tags.

The mapper then outputs key-value pairs with the journal name and publication year as the key and a value of 1. This allows the reducer to count the number of articles published in each journal for each year.

The reducer then aggregates the counts for each key (journal and year) and outputs the final result as a list of journals and the number of articles published in each journal for each year.

# Getting Started
To get started, simply clone this repository, build the jar file and run the following command: <br>
* `hadoop jar MapReduceDBLP.jar /input_directory /output_directory`
<br><br>
Replace input_directory with the directory containing the DBLP data and output_directory with the directory where you want to save the output.
# Results
The output of this project is a list of journals and the number of articles published in each journal for each year. 
<br>Sample output looks like this,

``ACM Journal on Computing and Cultural Heritage-2018   20``

# Conclusion
By implementing MapReduce on DBLP publication data, we can gain valuable insights into academic publishing trends and patterns. This project provides a simple and efficient way to compute the number of articles published in each journal per year.
