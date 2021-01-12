package com.feature.records;

public class TestData {

    public static void main(String[] args) {
        //Data data = new Data(12, "john");
        Data data = new Data("john");
        System.out.printf("age = %s\nname = %s\n", data.age(), data.name());
        System.out.println(data);
        if (data.name() instanceof String str) {
            System.out.println("name is string " + str.toString());
        }

        String html = """
                <html>
                    <body>
                        <h1>test html</h1>
                    </body>
                </html>
                """;
        System.out.println(html);
    }
}
