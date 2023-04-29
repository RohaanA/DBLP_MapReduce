package com.itshoney.main;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
public class WordCount {
	public static void main(String[] args) throws Exception
	{
		Configuration conf = new Configuration();
		String[] pathArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		if (pathArgs.length < 2)
		{
			System.err.println("Usage: dblp <input-path> […] <output-path>");
			System.exit(2);
		}
		Job wcJob = Job.getInstance(conf, "PDC Q1");
		wcJob.setJarByClass(WordCount.class);
		wcJob.setMapperClass(WordCountMapper.class);
		wcJob.setCombinerClass(WordCountReducer.class);
		wcJob.setReducerClass(WordCountReducer.class);
		wcJob.setOutputKeyClass(Text.class);
		wcJob.setOutputValueClass(IntWritable.class);
		// Special settings for XML Reading
		conf.set("xmlinput.start", "<article");          
		conf.set("xmlinput.end", "</article>");
		wcJob.setInputFormatClass(XmlInputFormat.class);
		for (int i = 0; i < pathArgs.length - 1; ++i)
		{
		FileInputFormat.addInputPath(wcJob, new Path(pathArgs[i]));
		}
		FileOutputFormat.setOutputPath(wcJob, new Path(pathArgs[pathArgs.length - 1]));
		System.exit(wcJob.waitForCompletion(true) ? 0 : 1);
		}
}
