package com.service.impl;

import com.dao.HelpDao;
import com.service.HelpService;

public class HelpServiceImpl implements HelpService {

	private HelpDao helpDao;

	public HelpDao getHelpDao() {
		return helpDao;
	}

	public void setHelpDao(HelpDao helpDao) {
		this.helpDao = helpDao;
	}
	
}
