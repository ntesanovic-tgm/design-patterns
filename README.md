# Design Patterns

### Inhaltsverzeichnis

[Strategy Pattern](#Strategy)

## OO Basics

### Abstraction



### Encapsulation



### Polymorphism



### Inheritence



## OO Principles

### encapsulate what varies

Alles was sich ändern kann soll zusammengefügt werden und somit getrennt von sich nicht-änderndem Code werden. Somit sollen dann Nebeneffekte und unerwünschte Änderungen bzw. Probleme vermeiden.

### composition > inheritance

Man soll Kapselung der Vererbung vorziehen, da Vererbung nicht wirklich dynamisch ist.

### interfaces not implementations

Anstatt vorhandenen Code immer wieder zu ändern, sollte man eher auf Interfaces programmieren. Hier geht man nach dem Prinzip: "never touch a running system(code)"

### loosely coupled

Hat man Objekte, welche zusammen arbeiten, soll man diese möglichst unabhängig voneinander gestalten wie nur möglich. <small>(Observer)</small>

### open for extension - closed for modification

Man soll die Möglichkeit haben Klassen zu erweitern, um z.B. neue Ansprüche zu erfüllen. Allerdings soll währenddessen der bereits vorhandene Code nicht geändert werden. <small>(Decorator)</small>

### depend upon abstractions

Eine High-Level Komponente sollte nicht von Low-Level Komponenten abhängig sein. Hiermit ist gemeint, dass eine Klasse (High-Level) nicht von von vielen konkreten Klassen (Low-Level) abhängig sein soll. Dieses Prinzip kann man erfüllen, indem die Low-Level Komponenten von einer abstrakten Klasse erben. Die High-Level Komponente kann dann diese Klasse nutzen um nicht mehr von den ganzen Low-Level Komponenten abhängig zu sein.

## Patterns

### Strategy

**Problem:**
Wir haben eine Superklasse und die dazugehörigen Unterklassen. Wir wollen nun eine Methode hinzufügen. Allerdings fällt uns auf, dass nicht jede Unterklasse die Methode auf die gleiche Art und Weise benutzen soll. Somit müssen wir nun einen Weg finden, diese Methode an die jeweilige Unterklasse anzupassen. Also die erste Unterklasse hat die Methode in der Variante A, die zweite in der Variante B usw. Natürlich kann eine Variante auch mehrmals benutzt werden.

**Lösung:**

Es gebe jetzt einige verschiedene Möglichkeit dieses Problem zu lösen. Allerdings haben die Wege, welche den meisten Leuten einfallen, immer auch weitere Probleme.

1. *Methode in der Superklasse:*  
Mit einer Methode in der Superklasse die erstellt und implementiert, haben zwar alle Unterklassen, welche die Methode benötigten, diese, allerdings hat auch jede andere Unterklasse diese Methode dann. Dies ist nicht gut, da nicht alle Unterklassen die Methode gleich implementieren wollen oder sie gar überhaupt nicht haben wollen. Für jede einzelne dieser müsste man die Methode überschreiben. Somit würde dieser Lösungsvorschlag vor allem bei größeren Projekten nicht zu empfehlen.
2. *Interfaces:*  
Eine weitere Möglichkeit wären Interfaces für jede dieser benötigten Methoden. Hier müssten alle Unterklassen, welche die Methode benötigen, das Interface implementieren. Hier haben wir aber das Problem, dass jede Unterklasse nun die Methode neu erstellen müsste. Dadurch kann es sein, dass der gleiche Code mehrmals auftaucht, in den verschiedenen Klassen. Würde man diesen dann auch noch ändern wollen, müsste man dies in jeder Unterklasse tun. Das alles führt zu einer weiteren nicht idealen Lösung.
3. ***Strategy Pattern:***  
Beim Strategy Pattern werden nun die ersten 3 OO Principles umgesetzt. **Z**uerst wird alles, was sich ändern kann, aussortiert und in einen seperaten "Bereich" (Klassen, ...) gepackt. Somit befindet sich nun alles, was sich ändern kann, außerhalb der Klassenhierarchie. Bei Änderungen muss diese nicht mehr geändert werden. **I**n dem seperaten "Bereich" haben wir nun ein Interface. In diesem Interface befindet sich nur die eine Methode, welche mehrere Implementationen haben soll. Nun erstellt man für jede Variante der Methode eine Klasse, welche das Interface implementiert. **D**araufhin kann man nun in der Superklasse ein Objekt mit der Variante der Methode erstellen (ich erstelle eine Objekt von KlasseA, da ich die Variante A der Methode benötige). Mit Setter-Methoden kann man die Variante auch während der Laufzeit ändern, was bei der Vererbung nicht möglich wäre.

**Beispiel & UML:**

![Strategy](https://github.com/TGM-HIT/sew4-design-patterns-ntesanovic-tgm/blob/master/images/strategy.png)

Dieses Bild zeigt ein Beispiel und UML-Diagramm aus dem Buch "Head First Design Patterns". Hier haben wir die Superklasse "Duck", also Ente. Allerdings gibt es auch Unterklasse bzw. Arten von Enten. Nicht jede Entenart kann fliegen. Somit wurde für die Methode "fly", also fliegen, ein Interface mit Klassen welche diese Methode implementieren, erstellt. Das selbe gilt auch für das Quacken von Enten. Somit kann sich jede Entenart ihr "Quackbehavior" und "Flybehavior" aussuchen. Hierfür werden dann HAS-A-Beziehungen genutzt. IS-A-Beziehungen gibt es bei Vererbung. Die Mallord Ente ist eine Ente (sie erbt von "Duck"). Bei den Behaviors haben wir eine HAS-A-Beziehung, dass heißt, dass eine Ente ein Flugverhalten. Eine Ente ist kein Flugverhalten, aber hat ein Flugverhalten. Das bedeutet, dass hier in der Superklasse ein Attribut vom Typ Flybehavior und eines vom Typ Quackbehavior deklariert wird. Um diese dann während der Laufzeit ändern zu können, hat man dann Setter-Methoden und man kann sie auch in dem Konstruktor initialisieren. Somit kann man dann für jede vorhandene Ente und auch für jede neu hinzugefügte Ente das Verhalten bestimmen, ohne das Code mehrfach vorkommt und das auch während der Laufzeit. Falls sich plötlich eine fliegende Ente einen Flügel bricht, wird sie nicht mehr fliegen können.

**Beispielcode:**

~~~java

~~~

### Observer

**Problem:**

Bei dem Observer Pattern werden gibt es ein Objekt(Subjekt), dessen Daten sich ändern. Dazu gibt es auch Objekte, welche das Subjekt beobachten, die Observer. Diese wollen informiert werden, wenn sich die Daten des Subjektes ändern. Darauf hin erhalten die Observer auch die geänderten Daten. Es können mehrere Observer ein Subjekt beobachten. <small> Des Weiteren können die Beobachter sich anmelden(um die Daten zu erhalten, anmelden zum beobachten), abmelden(falls man die Daten nicht mehr haben will) und müssen natürlich informiert werden. Dies ist ähnlich wie bei einem Abonnement. </small>
Hier kommt außerdem ein neues OO Principle ins Spiel: *strive for loosely coupled designs between objects that interact*. Wenn sich ein Observer abmeldet, sollen die anderen weiterhin ihre Daten erhalten. Der Betrieb geht weiter, da das Subjekt nicht von den Observern abhängig ist und auch umgekehrt.

**Lösung:**

Auch beim Observer Pattern gibt es wieder mehr als nur eine Möglichkeit das Problem zu lösen.

1. *Java Observable*  
Bei dieser Lösung wird die in Java eingebaute Version des Observer Patterns verwendet. Das Problem bei dieser Variante des Observer Patterns ist, dass java.util.Observable eine Klasse ist. Das heißt, dass einige OO Principles wieder missachtet werden, da wir Vererbung statt Interfaces benutzen müssen.
![Observable](https://github.com/TGM-HIT/sew4-design-patterns-ntesanovic-tgm/blob/master/images/java_observable.png)
2. ***Observer Pattern*** <small>(aus dem Buch)</small>  
![Observer_Pattern](https://github.com/TGM-HIT/sew4-design-patterns-ntesanovic-tgm/blob/master/images/observer.png)
Hier haben wir nun die vom Buch vorgeschlagene Variante. Wir haben ein Interface für das Subjekt und wir haben ein Interface für die Observer. Es wird gezeigt, dass *Subject* eine HAS-A-Beziehung zu *Observer* hat. Das heißt, dass das Subjekt Beobachter hat. Allerdings gibt es hier auch noch eine HAS-A-Beziehung zwischen den konkreten Implementierungen. Dies ist etwas, was wir schon vorher als eher unerwünscht betrachtet haben. Allerdings kann man dadurch eine notify-Methode erstellen ohne Attribute. <small> Wieso dies so wichtig ist, weiß ich gerade auch nicht. </small>

**Beispiel:**

![Observer_Pattern_Beispiel](https://github.com/TGM-HIT/sew4-design-patterns-ntesanovic-tgm/blob/master/images/observer_example.png)

In diesem Beispiel wollen wir die Daten einer Wetterstation erhalten und diese dann an alle Observer weitergeben. Wenn sich also das Wetter ändert, werden alle registrierten Beobachter, benachrichtigt und, da jedes der Displays schon ein WeatherData-Objekt besitzt, muss für *update* auch kein Parameter übergeben werden, um zu wissen, bei welchem Objekt sich die Daten geändert haben. Daraufhin können die Daten dann ausgegeben werden.  
Das Observer Pattern wird außerdem bei z.B. Java Swing verwendet -> Listeners.

**Beispielcode:**

### Decorator

**Problem:**

Wir haben eine Basisklasse und einige konkretisierte Klassen. Nun wollen wir diese Subklassen dynamisch erweitern können bzw. dekorieren können. Das heißt, wir haben zum Beispiel eine Klasse, welches Text einlesen und dann etwas entsprechendes ausgeben kann und wir wollen nun Möglichkeiten erstellen um die Ausgabe nur in Klein - oder Großbuchstaben auszugeben.

Als Beispiel hatten wir den Café-Shop, welcher die verschiedenen Extras(Mocha, Whip, ...), zu den Kaffees hinzufügen musste.

**Lösung:**

Wie schon zuvor haben wir wieder mehrere Möglichkeiten, dieses Problem zu lösen.

1. *Klassen für alle Möglichkeiten:*  

Eine der erwähnten Möglichkeiten ist die Erstellung von Klassen für jede einzelne Kombination von Kaffees und Extras. Allerdings führt dies zu einer "Klassenexplosion". Es entsteht eine Unmenge an Klassen und falls dann eine Zutat sich, vom Preis, ändert, muss man somit alle Klassen ändern, welche diese Zutat benutzt haben.

2. *Attribute und Vererbung:*  

Das vererben der Attribute und diese immer auf *TRUE* oder *FALSE* hört sich auf jeden Fall schon besser an. Allerdings hat auch diese Methode einige Probleme. Hierzu zählt zum Beispiel, dass bei dem Hinzufügen von neuen Zutaten nicht nur neue Getter - und Setter-Methoden implementiert werden müssen, sonder auch, dass die Kostenfunktion umgeschrieben werden muss.

3. ***Decorator Pattern:***  

Und nun kommen wir zum Decorator Pattern, mit welchem wir auch das Design Principle **open for extension - closed for modification**. Indem man die Zutaten um den Kaffee "wrappet", kann man, ohne die Benutzung von Attributen oder unzähligen Klassen, die Kosten ganz leicht ausrechnen.

**UML & Beispielcode: **

![Observer_Pattern_Beispiel](https://github.com/TGM-HIT/sew4-design-patterns-ntesanovic-tgm/blob/master/images/decorator_pattern.PNG)

~~~java

public abstract class Beverage {
	String description = "Unknown Beverage";

	public String getDescription() {
		return description;
	}

	public abstract double cost();
}

public abstract class CondimentDecorator extends Beverage {
	public abstract String getDescription();
}

public class HouseBlend extends Beverage {
	public HouseBlend() {
		description = "House Blend Coffee";
	}

	public double cost() {
		return .89;
	}
}

public class Mocha extends CondimentDecorator {
	Beverage beverage;

	public Mocha(Beverage beverage) {
		this.beverage = beverage;
	}

	public String getDescription() {
		return beverage.getDescription() + ", Mocha";
	}

	public double cost() {
		return .20 + beverage.cost();
	}
}

public class StarbuzzCoffee {
	public static void main(String args[]) {
		Beverage beverage = new HouseBlend();
        beverage = new Mocha(beverage);
		System.out.println(beverage.getDescription()
				+ " $" + beverage.cost());
	}
}

~~~

### Factory

Wie schon bekannt ist, gilt das Principle *program to an interface, not an implementation*. Allerdings brechen wir dieses jedes mal wenn wir *new* benutzen, da man hier eine konkrete Klasse angeben muss, da Interfaces und abstrakte Klassen nicht instanzierbar sind. Für dieses Problem gibt es nun mehrere Lösungsmöglichkeiten.

**Simple Factory**

Die erste vorgestellte Möglichkeit ist die Simple Factory, welche eigentlich kein richtiges Design Pattern ist, sondern nur ein Idiom (immer wieder auftauchndes Konstrukt). Z.B. haben wir eine Methode mit einem Parameter, wobei der Parameter das Objekt bestimmt. Allerdings können neue Objekte hinzu kommen, wie zum Beispiel neue Gerichte in einem Restaurant o.ä., wodurch man diese auch bei der Methode berücksichtigen muss. Dadurch haben wir Code, welcher sich ändern kann. Dieser sollte in einer anderen Klasse abgelegt werden, anstatt mit nicht-verändertem Code zusammen zu lassen.

#### Factory Method

Bei dem Factory Method Pattern haben wir eine abstrakte Klasse (Creator/Factory), welche *factoryMethod* als abstrakte Methode besitzt. Außerdem kann die Klasse weitere Methoden haben, welche mit dem Ergebnis der *factoryMethod* arbeiten. In diesem Pattern "entscheiden" die Subklassen (von der Creator-Klasse -> konkrete Creator/Factory), welches konkrete Objekt erstellt wird. Hiermit wird ausgedrückt, dass der Creator ohne Wissen über das eigentlich Ergebnis, geschrieben wurde und nicht weiß womit genau er arbeiten wird.<small>Es wäre auch möglich eine Standardvariante der *factoryMethod* festzulegen.</small>
Z.B. haben wir nun wieder einen PizzaStore (Creator), welcher mit Pizzen (abstrakte Klasse für das Produkt) arbeitet und nicht mit den konkreten Pizzen. Er bekommt zwar eine konkrete Pizza zum arbeiten, weiß aber nicht, welche Pizza es ist und dies braucht er auch nicht zu wissen. So lange alle wichtigen Methoden der Pizza in der abstrakten Klasse definiert wurden, kann er jede konkrete Pizza gleich behandeln.

#### Abstract Factory

Mit einer Abstract Factory hat man die Möglichkeit, Familien von Produkten bzw. Objekten zu erstellen. Wir haben also eine abstrakte Klasse, welche unsere Factory ist. In dieser werden die Methoden deklariert, welche für die Erstellung der einzelnen Prduktfamilienmitglieder zuständig sind. Die Subklassen dieser AbstractFactory kümmern sich um die Erzeugung der Objekte bzw. sie geben an welches konkrete Produkt erstellt wird. Somit kann man durch das Erzeugen einer der SubFactories bei einem Programm festlegen, welche Produkte es erhält. Oft wird für die konkreten Factories das Factory Method Pattern benutzt.
Bsp.: Wir haben wieder einen PizzaStore, allerdings schauen wir dieses mal auf die Zutaten. Wir haben also eine AbstractFactory mit create-Methoden für die Zutaten wie Käse, Sauce, .... All diese Zutaten gehören zu der *Zutatenfamilie*. In den Subklassen werden nun die bereits definierten create-Methoden genutzt, um die jeweiligen Zutaten zu Erstellen. Nun wollen wir eine Margherita bzw. Käse Pizza. Wir übergeben der Käse Pizza-Klasse die jeweilige Subklassen-Factory, welche benötigt wird und dann kann durch aufrufen der create-Methoden die richtige Zutatenfamilie erhalten werden.

#### Factory Method vs. Abstract Factory

Der Unterschied zwischen diesen beiden ist, dass das Factory Method Pattern Subklassen benutzt, welche die Erstellung von konkreten Objekte für den Client übernehmen, damit dieser sich nur noch auf die abstrakte Klasse konzentirieren muss.
Währenddessen bietet bei Abstract Factory eine abstrakte Klasse Methoden bzw. Möglichkeiten um eine Familie von Produkten zu erzeugen. Die Art, wie diese Produkte erzeugt werden, wird dann von den konkreten Klassen festgelegt. Und um diese dann nutzen zu können, muss man eine konkrete Factory initialisieren und dann Code übergeben werden, welcher mithilfe der Factory die Produkte erstellt bzw. erhaltet.
