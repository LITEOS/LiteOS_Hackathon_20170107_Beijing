package org.levainservice.rest.oms;

import org.levainservice.rest.RESTClient;
import org.levainservice.rest.Response;
import org.levainservice.service.oms.model.Contact;
import org.levainservice.util.IdRandom;

import com.google.gson.Gson;

public class ContactClient extends BaseClient {
	private static final String RESOURCE_PATH = "/contactService";

	public Response insert(Contact contact) {
		long id = Long.parseLong(IdRandom.getId());
		contact.setContactId(id);
		String json = gson.toJson(contact);
		String servicePort = BaseClient.SERVICE_BASE_URL + RESOURCE_PATH + "/";

		Response response = client.request(servicePort,
				RESTClient.SUBMIT_METHOD_POST, json);
		return response;
	}

	public Response listContacts(String deviceSerialNo) {

		String servicePort = BaseClient.SERVICE_BASE_URL + RESOURCE_PATH + "/"
				+ deviceSerialNo;

		Response response = client.request(servicePort,
				RESTClient.SUBMIT_METHOD_GET, "");
		return response;
	}

	public static void main(String[] args) {
		ContactClient client = new ContactClient();
		
		Response r = client.listContacts("OMS-0001");
		System.out.println(new Gson().toJson(r));
	}
}
