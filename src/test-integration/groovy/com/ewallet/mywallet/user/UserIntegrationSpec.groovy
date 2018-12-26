package com.ewallet.mywallet.user

import com.ewallet.mywallet.AbstractIntegrationSpec
import com.ewallet.mywallet.userdetails.UserDetailsVo
import com.github.springtestdbunit.annotation.DatabaseOperation
import com.github.springtestdbunit.annotation.DatabaseSetup
import groovy.json.JsonSlurper
import io.restassured.http.ContentType
import org.apache.http.HttpStatus
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.annotation.DirtiesContext
import spock.lang.Shared

import static io.restassured.RestAssured.given

@DatabaseSetup(value = "classpath:dataset/UserData.xml", type = DatabaseOperation.CLEAN_INSERT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserIntegrationSpec extends AbstractIntegrationSpec {

    @LocalServerPort
    private int port

    @Shared
    private JsonSlurper jsonSlurper = new JsonSlurper()

    def "Get User by username"() {
        expect:
        def response = given()
        .port(port)
        .when()
        .get("/ewallet/username/arpit")
        .then()
        .contentType(ContentType.JSON)
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .response()

        def jsonResponse = jsonSlurper.parseText(response.asString())
        jsonResponse.name == "Arpit"
        jsonResponse.walletBalance == 52000
        jsonResponse.userName == "arpit"
        jsonResponse.email == "arpit@gmail.com"

    }

    def "Get user by mobile no"() {
        expect:
        def response = given()
                        .port(port)
                        .when()
                        .get("/ewallet/mobileno/456")
                        .then()
                        .contentType(ContentType.JSON)
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .response()

        def jsonResponse = jsonSlurper.parseText(response.asString())

        jsonResponse.name == "Shivam"
        jsonResponse.userName == "shivam"
        jsonResponse.email == "shivam@gmail.com"
        jsonResponse.walletBalance == 38000

    }

    def "Register a User"() {
        given:
        UserDetailsVo userDetailsVo = UserDetailsVo.builder()
                .name("Pooja")
                .email("pooja@gmail.com")
                .password("hello123")
                .walletBalance(new BigDecimal(31000))
                .userName("pooja")
                .mobileNo("001")
                .build()

        expect:
            def response = given()
                        .port(port)
                        .contentType(ContentType.JSON)
                        .body(userDetailsVo)
                        .when()
                        .post("/ewallet")
                        .then()
                        .contentType(ContentType.JSON)
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .response()



        def jsonResponse = jsonSlurper.parseText(response.asString())

        jsonResponse.name == "Pooja"
        jsonResponse.uuid != null
        jsonResponse.userName == "pooja"
        jsonResponse.walletBalance == 31000
        jsonResponse.email == "pooja@gmail.com"
        jsonResponse.mobileNo == "001"
    }

    def "Add balance to a user Wallet"() {
        expect:
        def response = given()
                        .port(port)
                        .when()
                        .put("/ewallet/addbalance/arpit/58000")
                        .then()
                        .contentType(ContentType.JSON)
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .response()

        def jsonResponse = jsonSlurper.parseText(response.asString())

        jsonResponse.name == "Arpit"
        jsonResponse.uuid == "test_uuid1"
        jsonResponse.email == "arpit@gmail.com"
        jsonResponse.userName == "arpit"
        jsonResponse.walletBalance == 110000
        jsonResponse.mobileNo == "123"
    }

    def "Withdraw balance from the User Wallet"(){

        expect:
        def response = given()
                .port(port)
                .when()
                .put("/ewallet/withdrawbalance/arpit/2000")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response()

        def jsonResponse = jsonSlurper.parseText(response.asString())

        jsonResponse.name == "Arpit"
        jsonResponse.uuid == "test_uuid1"
        jsonResponse.email == "arpit@gmail.com"
        jsonResponse.userName == "arpit"
        jsonResponse.walletBalance == 50000
        jsonResponse.mobileNo == "123"

    }
}
