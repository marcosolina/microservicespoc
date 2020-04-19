package com.marco.marcoreactui.services.implementations;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;

import com.marco.marcoreactui.config.properties.MenuServiceProperties;
import com.marco.marcoreactui.dto.menu.ApiMenu;
import com.marco.marcoreactui.dto.menu.ApiMenus;
import com.marco.marcoreactui.services.interfaces.MenusBusinsessLogicInt;
import com.marco.marcoreactui.services.interfaces.RestClientInt;
import com.marco.marcoreactui.utils.ReactUiConstants;

public class MenusBusinsessLogicImpl implements MenusBusinsessLogicInt {

	@Autowired
	private RestClientInt wsClient;

	@Autowired
	private MenuServiceProperties props;

	@Override
	public List<ApiMenu> getAllMenus() {
		URL url;
		try {
			url = new URL(props.getProtocol(), props.getHost(), props.getFindAllMenus());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			// TODO manage the error in a better way
			return null;
		}
		ClientResponse resp = wsClient.performGetRequest(Optional.of(ReactUiConstants.TOKEN_MENU_REGISTRATION_ID), url, Optional.empty(), Optional.empty());
		if (resp.statusCode() == HttpStatus.OK) {
			ApiMenus menus = wsClient.getBodyFromResponse(resp, ApiMenus.class);
			return menus.getMenus();
		}
		return null;
	}

	@Override
	public boolean insertMenu(ApiMenu newMenu) {
		URL url;
		try {
			url = new URL(props.getProtocol(), props.getHost(), props.getInsertMenu());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			// TODO manage the error in a better way
			return false;
		}
		ClientResponse resp = wsClient.performPostRequest(Optional.of(ReactUiConstants.TOKEN_MENU_REGISTRATION_ID), url, Optional.empty(), Optional.empty(), Optional.of(newMenu));
		return resp != null && resp.statusCode() == HttpStatus.CREATED;
	}

	@Override
	public boolean deleteMenu(String menuName) {
		URL url;
		try {
			url = new URL(props.getProtocol(), props.getHost(), props.getDeleteMenu(menuName));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			// TODO manage the error in a better way
			return false;
		}
		ClientResponse resp = wsClient.performDeleteRequest(Optional.of(ReactUiConstants.TOKEN_MENU_REGISTRATION_ID), url, Optional.empty(), Optional.empty(), Optional.empty());
		return resp != null && resp.statusCode() == HttpStatus.NO_CONTENT;
	}

}
