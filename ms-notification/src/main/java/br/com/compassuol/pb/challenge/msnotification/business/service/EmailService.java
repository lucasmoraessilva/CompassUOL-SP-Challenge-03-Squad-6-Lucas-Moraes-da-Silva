package br.com.compassuol.pb.challenge.msnotification.business.service;

import br.com.compassuol.pb.challenge.msnotification.model.entity.UserModel;

public interface EmailService {
    public void sendEmailCreatedUser(UserModel userDetails);
    public void sendEmailUpdatedUser(UserModel userDetails);
    public void sendEmailDeletedUser(UserModel userDetails);
}
