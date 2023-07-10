package br.com.compassuol.pb.challenge.msproducts.message.broker;

import br.com.compassuol.pb.challenge.msproducts.model.entity.UserModel;

public interface UserMessageBroker {
    public void emailCreatedUser(UserModel createdUser);
    public void emailUpdatedUser(UserModel updatedUser);
    public void emailDeletedUser(UserModel deletedUser);
}
