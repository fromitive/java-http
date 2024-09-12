package org.apache.coyote.http11.controller;

import com.techcourse.db.InMemoryUserRepository;
import com.techcourse.model.User;
import org.apache.coyote.http11.AbstractController;
import org.apache.coyote.http11.HttpRequest;
import org.apache.coyote.http11.HttpResponse;
import org.apache.coyote.http11.HttpStatusCode;
import org.apache.coyote.http11.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegisterController extends AbstractController {

    private static final String USER_SESSION_INFO_NAME = "user";
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Override
    protected void doGet(HttpRequest request, HttpResponse response) {
        response.statusCode(HttpStatusCode.OK)
                .staticResource("/register.html");
    }

    @Override
    protected void doPost(HttpRequest request, HttpResponse response) {
        Parameter param = request.getParameter();
        User newAccount = new User(param.getValue("account"), param.getValue("password"), param.getValue("email"));
        logger.info("user : {}", newAccount);
        InMemoryUserRepository.save(newAccount);
        response.statusCode(HttpStatusCode.FOUND)
                .createSession(USER_SESSION_INFO_NAME, newAccount)
                .redirect("index.html");
    }
}
