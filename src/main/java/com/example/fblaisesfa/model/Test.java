package com.example.fblaisesfa.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.fblaisesfa.exception.GenerateOutOfBoundOperationNumberException;

public class Test {

	public static void main(String[] args) {
		SimpleDateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
		String dateAsString = dfm.format(new Date());
		String[] splitedDate = dateAsString.split("-");
		String year = splitedDate[0].substring(2);
		String commandNumber = "CM" + year + "002";
		Long counter = 99999L;
		String counterAsString = Long.toString(counter+1);
		switch (counterAsString.length()) {
		case 1:
			commandNumber = commandNumber + "0000" + counterAsString;
			System.out.println("The Command number is " + commandNumber);
			break;
		case 2:
			commandNumber = commandNumber + "000" + counterAsString;
			System.out.println("The Command number is " + commandNumber);
			break;
		case 3:
			commandNumber = commandNumber + "00" + counterAsString;
			System.out.println("The Command number is " + commandNumber);
			break;
		case 4:
			commandNumber = commandNumber + "0" + counterAsString;
			System.out.println("The Command number is " + commandNumber);
			break;
		case 5:
			commandNumber = commandNumber + counterAsString;
			System.out.println("The Command number is " + commandNumber);
			break;
		default:
			throw new GenerateOutOfBoundOperationNumberException();
		}

		// List<Counter> counters = counterRepository.findAll();
		// Counter counter = null;
		// if (counters.isEmpty()) {
		// counter = new Counter(0L, 0L);
		// counter = counterRepository.save(counter);
		// } else {
		// counter = counters.get(0);
		// }
		// String counterAsString = Long.toString(counter.getCommandCounter());
		// switch (counterAsString.length()) {
		//// case value:
		////
		//// break;
		//
		// default:
		// break;
		// }

	}

}
