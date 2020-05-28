package com.wcf.hive;

import java.io.*;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceStability;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.*;

import com.google.common.base.Charsets;
import org.apache.hadoop.mapred.*;

/**
 * @author 吴长飞
 * @create 2020-05-14 15:11
 **/



@InterfaceAudience.Public
@InterfaceStability.Stable
public class DIYInputFormat extends FileInputFormat<LongWritable, Text>
        implements JobConfigurable {

    private CompressionCodecFactory compressionCodecs = null;

    public void configure(JobConf conf) {
        compressionCodecs = new CompressionCodecFactory(conf);
    }

    protected boolean isSplitable(FileSystem fs, Path file) {
        final CompressionCodec codec = compressionCodecs.getCodec(file);
        if (null == codec) {
            return true;
        }
        return codec instanceof SplittableCompressionCodec;
    }

    public RecordReader<LongWritable, Text> getRecordReader(
            InputSplit genericSplit, JobConf job,
            Reporter reporter)
            throws IOException {

        reporter.setStatus(genericSplit.toString());
        String delimiter = "#^#@%$#";
        byte[] recordDelimiterBytes = null;
        if (null != delimiter) {
            recordDelimiterBytes = delimiter.getBytes(Charsets.UTF_8);
        }
        return new DIYLineRecordReader(job, (FileSplit) genericSplit,
                recordDelimiterBytes);
    }
}

