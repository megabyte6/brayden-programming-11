& 'C:\Program Files\Java\jdk-1.8.0_281\bin\javac.exe' .\src\DataStore.java .\src\Easy_Controller.java .\src\Hard_Controller.java .\src\Instructions_Controller.java .\src\Main.java .\src\Medium_Controller.java .\src\Start_Controller.java
Move-Item  -Path '.\src\*.class' -Destination '.\bin\' -Force
& 'C:\Program Files\Java\jdk-16\bin\jar.exe' -c -f .\BingoWithJavaFX.jar -m .\MANIFEST.MF -C .\src . -C .\bin .