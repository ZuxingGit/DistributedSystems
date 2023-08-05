Hello,
execute these commands one by one:

1. make
compile all .java to .class

2. make register
it will execute rmiregistry in destDir where .class files were exported
Then you might have to wait for about 30s before next step.

3. make startServer

4. make startClient
The client will invoke different methods from Server.