package decorator_pattern;

public class Whip extends CondimentDecorator{

    Beverage beverage;

    public Whip(Beverage beverage){
        this.beverage = beverage;
    }

    public String getDescription(){
        return this.beverage.getDescription() + ", Whip";
    }

    public double cost(){
        return this.beverage.cost() + 0.10;
    }

}
