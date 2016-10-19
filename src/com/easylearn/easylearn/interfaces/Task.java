package com.easylearn.easylearn.interfaces;

import java.util.List;

public interface Task {
	String getQuestion();
	List<String> getAnswerOptions();
	void answer();
	boolean checkAnswer();
}
