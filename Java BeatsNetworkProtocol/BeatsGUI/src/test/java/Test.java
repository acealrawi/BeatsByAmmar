class Test {
    public static void main(String[] args) {
        Initiater initiater = new Initiater();
        Responder responder = new Responder();

        initiater.addListener(new HelloListener() {
            public void someoneSaidHello() {
                System.out.println("ali");

            }
        });

        initiater.sayHello();  // Prints "Hello!!!" and "Hello there..."
    }
}