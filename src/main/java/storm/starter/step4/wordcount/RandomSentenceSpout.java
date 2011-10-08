package storm.starter.step4.wordcount;

import java.util.Map;
import java.util.Random;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

public class RandomSentenceSpout implements IRichSpout {
	private static final long serialVersionUID = 1L;

	SpoutOutputCollector _collector;
	Random _rand;

	@Override
	public boolean isDistributed() {
		return true;
	}

	@Override
	public void open(@SuppressWarnings("rawtypes") final Map conf, final TopologyContext context,
			final SpoutOutputCollector collector) {
		_collector = collector;
		_rand = new Random();
	}

	@Override
	public void nextTuple() {
		Utils.sleep(100);
		final String[] sentences = new String[] { "the cow jumped over the moon",
				"an apple a day keeps the doctor away", "four score and seven years ago",
				"snow white and the seven dwarfs", "i am at two with nature" };
		final String sentence = sentences[_rand.nextInt(sentences.length)];
		_collector.emit(new Values(sentence));
	}

	@Override
	public void close() {
	}

	@Override
	public void ack(final Object id) {
	}

	@Override
	public void fail(final Object id) {
	}

	@Override
	public void declareOutputFields(final OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("word"));
	}

}