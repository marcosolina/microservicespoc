package com.marco.marcoreactui.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Standard Spring properties class
 * 
 * @author msolina
 *
 */
@Configuration
@ConfigurationProperties("rest.request.service.menus")
public class MenuServiceProperties {
	private String host;
	private String protocol;
	private String findMenuByName;
	private String findAllMenus;
	private String deleteMenu;
	private String insertMenu;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getFindMenuByName(String menuName) {
		return findMenuByName + "/" + menuName;
	}

	public void setFindMenuByName(String findMenuByName) {
		this.findMenuByName = findMenuByName;
	}

	public String getFindAllMenus() {
		return findAllMenus;
	}

	public void setFindAllMenus(String findAllMenus) {
		this.findAllMenus = findAllMenus;
	}

	public String getDeleteMenu(String menuName) {
		return deleteMenu + "/" + menuName;
	}

	public void setDeleteMenu(String deleteMenu) {
		this.deleteMenu = deleteMenu;
	}

	public String getInsertMenu() {
		return insertMenu;
	}

	public void setInsertMenu(String insertMenu) {
		this.insertMenu = insertMenu;
	}

}
