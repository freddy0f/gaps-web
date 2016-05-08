package org.ec.id.gaps.util;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.core.Response;

class ResponseUtil {

	public static Response seeOther(String url) {
		try {
			return Response.seeOther(new URI(url)).build();
		} catch (URISyntaxException e) {
			return null;
		}
	}
}
