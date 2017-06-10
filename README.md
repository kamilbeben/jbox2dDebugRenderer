# Jbox2D Debug Renderer

This renderer is adapted to Jbox2D version 2.2.1.1
It will probably work with other versions, but it may need a few changes
You can use it as you want, you don't have to ask me for permission. Just please mention the original creator.
If you have any questions, or bugs to report contact me at kamilbeben94@gmail.com

Currently implemented shapes:
CircleShape,
PolygonShape,
EdgeShape
and joints.


## Example of uses:

### Quick start, with default settings
```java
    public Physics() {
        world = new World(gravity);
        new JBox2DDebugRenderer(world, 5);
    }
```
### Customizable
```java
    public Physics() {
        world = new World(gravity);
        Jbox2DDebugConfig config = new Jbox2DDebugConfig();
        config.setPixelsPerUnit(PPU);
	config.setColorPolygon(Color.BLUE);
        new JBox2DDebugRenderer(world, config, 5);
    }
```
#### You can set-up following things:
```java
    public void setTitle(String title);

    public void setColorBackground(Color colorBackground);

    public void setColorPolygon(Color colorPolygon);

    public void setColorCircle(Color colorCircle);

    public void setColorEdge(Color colorEdge);

    public void setAlpha(float alpha);
```
