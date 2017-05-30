package com.dn.crawler.blacklist.core;

import java.util.List;

public interface Parser<T> {
	public List<T> parse(String content);
}
