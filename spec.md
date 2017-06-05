# Goo Spec

Goo is a lightweight markup language for designing cross platform mobile user interfaces.

## Vector

A point or size in two dimensional space.

### x: Number

Position or size on the horizontal x axis.

### y: Number

Position or size on the vertical y axis.

## Bounds

A bounding box defined by minimum (top left) and maximum (bottom right) points.

### min: Vector

The top left position.

### max: Vector

The bottom left position.

## Inset

### left: Number

### right: Number

### top: Number

### bottom: Number

## View

A basic user interface element. A rectangle that can be positioned within other views.

### id: String

An non-unique identifier used to query views from a layout.

### anchor: Bounds or Enum

Views are laid out relative to their anchor. Anchors are normalized bounds mapped to the view’s parent. Anchors can also be defined in shorthand. (Maybe this can be inferred automatically?)

* top-left
* vertical-top
* top-right
* horizontal-left
* center
* horizontal-right
* bottom-left
* vertical-bottom
* bottom-right
* top-stretch
* horizontal-stretch
* bottom-stretch
* left-stretch
* vertical-stretch
* right-stretch
* stretch

### x: Number

Horizontal position relative to the anchor. Ignored if the horizontal anchor axis is stretched.

### y: Number

Vertical position relative to the anchor. Ignored if the vertical anchor axis is stretched.

### left: Number

Used to position views anchored to the left of their parent.

### right: Number

Used to position views anchors to the right of their parent.

### top: Number

Used to position views anchored to the top of their parent.

### bottom: Number

Used to position views anchored to the bottom of their parent.

### width: Number

The view's horizontal size. Ignored if the horizontal anchor axis is stretched.

### height: Number

The view's vertical size: Ignored if the horizontal anchor axis is stretched.

### layout: Layout

Auto layout rules for parents and children.

## Layout

Configures layout rules.

### min-width: Number

### max-width: Number

### preferred-width: Number

### preferred-height: Number

### horizontal-portion: Number

### vertical-portion: Number

### type: Enum

Parent layout type.

* vertical
* horizontal
* grid

### horizontal-fit: Enum

* minimum
* preferred

### vertical-fit: Enum

* minimum
* preferred

### padding: Inset

Adds padding around childen in a layout.

### spacing: Number or Vector

Defines the space between children. Grid layouts can use a Vector instead of a Number for different horizontal and vertical spacing.

### ignore: Bool

Ignore layout rules.