public class Wee {
    private static final Wee WEE = new Wee();
    public static final String NAME = "Wee";

    private Wee() {}

    public static void main(String[] args) {
        String logo = """
                ██     ██  ████████  ████████\s
                ██     ██  ██        ██      \s
                ██  █  ██  ██████    ██████  \s
                ██ ███ ██  ██        ██      \s
                 ███ ███   ████████  ████████
                """;
        System.out.println("Hello from\n" + logo);
        WEE.say("Hello! I'm " + NAME, "What can I do for you");
    }

    private void say(String... messages) {
        String speaker = NAME + " >> ";
        String indentation = " ".repeat(speaker.length());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < messages.length; i++) {
            String prefix = i == 0 ? speaker : indentation;
            stringBuilder.append(prefix).append(messages[i]).append("\n");
        }
        System.out.println(stringBuilder);
    }
}
