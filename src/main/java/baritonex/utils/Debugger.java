package baritonex.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum Debugger {
	INSTANCE;
	
	private final List<Section> sections;
	private final Logger logger;
	
	private Debugger() {
		this.sections = new ArrayList<>();
		this.logger = LogManager.getLogger("Debugger");
	}
	
	public void start(String str) {
		this.sections.add(new Section(str).start());
	}
	
	public void end() {
		this.sections.get(this.sections.size() - 1).end();
	}
	
	public void endStart(String name) {
		end();
		start(name);
	}
	
	private void log(Section section, String stonks) {
		this.logger.info(stonks, getName(section));
	}
	
	private String getName(Section section) {
		String name = section.getName();
		int index = this.sections.indexOf(section);
		if(index != 0) {
			while(index > 0) {
				index--;
				name = this.sections.get(index).getName() + "." + name;
			}
		}
		return name;
	}
	
	private class Section {
		private String name;
		private long start;
		
		public Section(String name) {
			this.name = name;
		}
		
		public Section start() {
			this.start = System.currentTimeMillis();
			return this;
		}
		
		public void end() {
			log(this, "Section took " + (System.currentTimeMillis() - this.start) + "ms");
			Debugger.INSTANCE.sections.remove(this);
		}
		
		public String getName() {
			return name;
		}
	}

}
