package br.com.apicadel.resources.utils;

/**
 * Enum utilizando para retornar código referente a mensagem para as views do
 * projeto Front
 * 
 * @author tarssito
 *
 */
public enum CodigoMensagem {

	COD_INSERT_SUCCESS("1"), COD_UPDATE_SUCCESS("2"), COD_DELETE_SUCCESS("3"), COD_OBJECT_NOTFOUND(
			"7"), COD_UNIQUE_REGISTER("8"), COD_DATA_INTEGRITY("11");

	private String codigoMsg;

	private CodigoMensagem(String codigoMsg) {
		this.codigoMsg = codigoMsg;
	}

	public String getCodigoMsg() {
		return codigoMsg;
	}

}
