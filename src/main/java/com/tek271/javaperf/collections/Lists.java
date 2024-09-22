package com.tek271.javaperf.collections;

import com.tek271.javaperf.utils.Prove;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.impl.list.mutable.primitive.IntArrayList;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.RandomUtils.insecure;

public class Lists {
	private static final int LIST_SIZE = 10_000;
	private static final int MAX_RANDOM = 1000_000;

	public void classic() {
		List<Integer> list = new ArrayList<>();
		for (int i=0; i < LIST_SIZE; i++) {
			list.add(randomInt());
		}

		int removed = 0;
		for (int i=0; i < LIST_SIZE/100; i++) {
			int value = randomInt();
			int index = list.indexOf(value);
			if (index >= 0) {
				list.remove(index);
				removed++;
			}
		}
		Prove.equal(LIST_SIZE, list.size() + removed, "Lists.classic");
	}

	private static int randomInt() {
		return insecure().randomInt(0, MAX_RANDOM);
	}

	public void primitives() {
		MutableIntList list = new IntArrayList();
		for (int i=0; i < LIST_SIZE; i++) {
			list.add(randomInt());
		}

		int removed = 0;
		for (int i=0; i < LIST_SIZE/100; i++) {
			int value = randomInt();
			int index = list.indexOf(value);
			if (index >= 0) {
				list.remove(value);
				removed++;
			}
		}
		Prove.equal(LIST_SIZE, list.size() + removed, "Lists.primitives");
	}


}
