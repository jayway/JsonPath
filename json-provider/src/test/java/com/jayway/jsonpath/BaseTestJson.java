package com.jayway.jsonpath;


public abstract class BaseTestJson {

	public static final String JSON_BOOK_DOCUMENT =
	"{ " +
	"   \"category\" : \"reference\",\n" +
	"   \"author\" : \"Nigel Rees\",\n" +
	"   \"title\" : \"Sayings of the Century\",\n" +
	"   \"display-price\" : 8.95\n" +
	"}";
	public static final String JSON_DOCUMENT = "{\n" +
	"   \"string-property\" : \"string-value\", \n" +
	"   \"int-max-property\" : " + Integer.MAX_VALUE + ", \n" +
	"   \"long-max-property\" : " + Long.MAX_VALUE + ", \n" +
	"   \"boolean-property\" : true, \n" +
	"   \"null-property\" : null, \n" +
	"   \"int-small-property\" : 1, \n" +
	"   \"max-price\" : 10, \n" +
	"   \"store\" : {\n" +
	"      \"book\" : [\n" +
	"         {\n" +
	"            \"category\" : \"reference\",\n" +
	"            \"author\" : \"Nigel Rees\",\n" +
	"            \"title\" : \"Sayings of the Century\",\n" +
	"            \"display-price\" : 8.95\n" +
	"         },\n" +
	"         {\n" +
	"            \"category\" : \"fiction\",\n" +
	"            \"author\" : \"Evelyn Waugh\",\n" +
	"            \"title\" : \"Sword of Honour\",\n" +
	"            \"display-price\" : 12.99\n" +
	"         },\n" +
	"         {\n" +
	"            \"category\" : \"fiction\",\n" +
	"            \"author\" : \"Herman Melville\",\n" +
	"            \"title\" : \"Moby Dick\",\n" +
	"            \"isbn\" : \"0-553-21311-3\",\n" +
	"            \"display-price\" : 8.99\n" +
	"         },\n" +
	"         {\n" +
	"            \"category\" : \"fiction\",\n" +
	"            \"author\" : \"J. R. R. Tolkien\",\n" +
	"            \"title\" : \"The Lord of the Rings\",\n" +
	"            \"isbn\" : \"0-395-19395-8\",\n" +
	"            \"display-price\" : 22.99\n" +
	"         }\n" +
	"      ],\n" +
	"      \"bicycle\" : {\n" +
	"         \"foo\" : \"baz\",\n" +
	"         \"escape\" : \"Esc\\b\\f\\n\\r\\t\\n\\t\\u002A\",\n" +
	"         \"color\" : \"red\",\n" +
	"         \"display-price\" : 19.95,\n" +
	"         \"foo:bar\" : \"fooBar\",\n" +
	"         \"dot.notation\" : \"new\",\n" +
	"         \"dash-notation\" : \"dashes\"\n" +
	"      }\n" +
	"   },\n" +
	"   \"foo\" : \"bar\",\n" +
	"   \"@id\" : \"ID\"\n" +
	"}";
}
