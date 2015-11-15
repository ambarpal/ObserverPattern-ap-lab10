package com.iiitd.ap.lab10;

public interface Subject{
	void registerObserver(Observer observer);
	void removeObserver(Observer observer);
	void notifyObservers();
	Object getUpdate();
}