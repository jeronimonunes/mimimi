all:
	mkdir target/classes -p
	javac -g -sourcepath src/main/java -classpath "lib/*" -d target/classes src/main/java/br/dcc/ufmg/pm/mimimi/Main.java
	cp src/main/resources/* -R target/classes/
clean:
	rm -rf target/classes/*

run:
	java -classpath target/classes:lib/* br.dcc.ufmg.pm.mimimi.Main 
