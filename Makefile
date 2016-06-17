all:
	javac -sourcepath src/main/java -classpath "lib/*" -d target/classes src/main/java/br/dcc/ufmg/pm/mimimi/Main.java

clean:
	rm -rf target/classes/*

run:
	java -classpath target/classes:lib/*:src/main/resources br.dcc.ufmg.pm.mimimi.Mimimi
