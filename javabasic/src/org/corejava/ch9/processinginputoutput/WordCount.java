package org.corejava.ch9.processinginputoutput;

import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.w3c.dom.Text;

public class WordCount {
	/***

	public static class Map extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();

		// mapper
		public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter)
				throws IOException {
			String line = value.toString();
			StringTokenizer tokenizer = new StringTokenizer(line);
			while (tokenizer.hasMoreTokens()) {
				word.set(tokenizer.nextToken());
				output.collect(word, one);
			}
		}

		// reducer
		public static class Reduce extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {
			public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException{
				int sum = 0;
				while (values.hasNext()) {
					sum+= values.next().get();
				}
				output.collect(key, new InteWritable(sum));
			}
		}

	}
	*/
}
