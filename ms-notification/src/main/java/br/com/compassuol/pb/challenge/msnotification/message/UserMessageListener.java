package br.com.compassuol.pb.challenge.msnotification.message;

public interface UserMessageListener {
    public void createdUser(String userInJsonFormat);
    public void updatedUser(String userInJsonFormat);
    public void deletedUser(String userInJsonFormat);
}
