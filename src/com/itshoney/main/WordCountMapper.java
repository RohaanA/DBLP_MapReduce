package com.itshoney.main;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;
import java.io.ByteArrayInputStream;
public class WordCountMapper extends Mapper<LongWritable, Text,Text, IntWritable> {
@Override
protected void map(LongWritable key, Text value,
                 Mapper.Context context)
  throws
  IOException, InterruptedException {
String document = value.toString();
System.out.println("‘" + document + "‘");
try {
  XMLStreamReader reader =
      XMLInputFactory.newInstance().createXMLStreamReader(new
          ByteArrayInputStream(document.getBytes()));
  String journalName = "";
  String articleYear = "";
  String currentElement = "";
  while (reader.hasNext()) {
    int code = reader.next();
    switch (code) {
      case XMLStreamConstants.START_ELEMENT:
        currentElement = reader.getLocalName();
        break;
      case XMLStreamConstants.CHARACTERS:
        if (currentElement.equalsIgnoreCase("journal")) {
          journalName += reader.getText();
        } else if (currentElement.equalsIgnoreCase("year")) {
          articleYear += reader.getText();
        }
        break;
    }
  }
  reader.close();
  
  String Hashkey = journalName + "-" + articleYear; 
  Text TextHash = new Text(Hashkey);
  context.write(TextHash, new IntWritable(1));
  
} catch (Exception e) {
  System.err.println("Error processing ‘" + document + "‘");
}
}
}