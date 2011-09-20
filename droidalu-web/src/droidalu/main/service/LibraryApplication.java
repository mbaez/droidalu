package droidalu.main.service;

import javax.ws.rs.core.Application;
import java.util.Set;
import java.util.HashSet;

public class LibraryApplication extends Application {
	HashSet<Object> singletons = new HashSet<Object>();

	public LibraryApplication() {
		singletons.add(new Library());
	}

	@Override
	public Set<Class<?>> getClasses() {
		HashSet<Class<?>> set = new HashSet<Class<?>>();
		return set;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
