interface ChatMediator {
    void sendMessage(String message, User user);
}

class ChatRoom implements ChatMediator {
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public void sendMessage(String message, User sender) {
        for (User user : users) {
            if (user != sender) {
                user.receive(message);
            }
        }
    }
}

abstract class User {
    protected ChatMediator mediator;
    protected String name;

    public User(ChatMediator med, String name) {
        this.mediator = med;
        this.name = name;
    }

    public abstract void send(String message);
    public abstract void receive(String message);
}

class Player extends User {
    public Player(ChatMediator med, String name) {
        super(med, name);
    }

    public void send(String message) {
        System.out.println(name + " sends: " + message);
        mediator.sendMessage(message, this);
    }

    public void receive(String message) {
        System.out.println(name + " receives: " + message);
    }
}

// Usage
public class MediatorDemo {
    public static void main(String[] args) {
        ChatRoom chat = new ChatRoom();

        User player1 = new Player(chat, "Knight");
        User player2 = new Player(chat, "Archer");

        chat.addUser(player1);
        chat.addUser(player2);

        player1.send("Hello!");
    }
}
