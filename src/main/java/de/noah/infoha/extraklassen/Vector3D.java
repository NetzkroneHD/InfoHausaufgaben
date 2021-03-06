package de.noah.infoha.extraklassen;

import com.google.common.base.Preconditions;

import java.util.*;
import java.util.function.Consumer;

/**
 * Represents a mutable vector. Because the components of Vectors are mutable,
 * storing Vectors long term may be dangerous if passing code modifies the
 * Vector later. If you want to keep around a Vector, it may be wise to call
 * <code>clone()</code> in order to get a copy.
 */
public class Vector3D implements Cloneable {
	
    private static final Random random = new Random();

    /**
     * Threshold for fuzzy equals().
     */
    private static final double epsilon = 0.000001;

    private double x, y, z;

    /**
     * Construct the vector with all components as 0.
     */
    public Vector3D() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    /**
     * Construct the vector with provided integer components.
     *
     * @param x X component
     * @param y Y component
     * @param z Z component
     */
    public Vector3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Construct the vector with provided double components.
     *
     * @param x X component
     * @param y Y component
     * @param z Z component
     */
    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Construct the vector with provided float components.
     *
     * @param x X component
     * @param y Y component
     * @param z Z component
     */
    public Vector3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Adds a vector to this one
     *
     * @param vec The other vector
     * @return the same vector
     */
    
    public Vector3D add(Vector3D vec) {
        x += vec.x;
        y += vec.y;
        z += vec.z;
        return this;
    }

    /**
     * Subtracts a vector from this one.
     *
     * @param vec The other vector
     * @return the same vector
     */
    
    public Vector3D subtract(Vector3D vec) {
        x -= vec.x;
        y -= vec.y;
        z -= vec.z;
        return this;
    }

    /**
     * Multiplies the vector by another.
     *
     * @param vec The other vector
     * @return the same vector
     */
    
    public Vector3D multiply(Vector3D vec) {
        x *= vec.x;
        y *= vec.y;
        z *= vec.z;
        return this;
    }

    /**
     * Divides the vector by another.
     *
     * @param vec The other vector
     * @return the same vector
     */
    
    public Vector3D divide(Vector3D vec) {
        x /= vec.x;
        y /= vec.y;
        z /= vec.z;
        return this;
    }

    /**
     * Copies another vector
     *
     * @param vec The other vector
     * @return the same vector
     */
    
    public Vector3D copy(Vector3D vec) {
        x = vec.x;
        y = vec.y;
        z = vec.z;
        return this;
    }

    /**
     * Gets the magnitude of the vector, defined as sqrt(x^2+y^2+z^2). The
     * value of this method is not cached and uses a costly square-root
     * function, so do not repeatedly call this method to get the vector's
     * magnitude. NaN will be returned if the inner result of the sqrt()
     * function overflows, which will be caused if the length is too long.
     *
     * @return the magnitude
     */
    public double length() {
        return Math.sqrt(square(x) + square(y) + square(z));
    }

    /**
     * Gets the magnitude of the vector squared.
     *
     * @return the magnitude
     */
    public double lengthSquared() {
        return square(x) + square(y) + square(z);
    }

    /**
     * Get the distance between this vector and another. The value of this
     * method is not cached and uses a costly square-root function, so do not
     * repeatedly call this method to get the vector's magnitude. NaN will be
     * returned if the inner result of the sqrt() function overflows, which
     * will be caused if the distance is too long.
     *
     * @param o The other vector
     * @return the distance
     */
    public double distance(Vector3D o) {
        return Math.sqrt(square(x - o.x) + square(y - o.y) + square(z - o.z));
    }

    /**
     * Get the squared distance between this vector and another.
     *
     * @param o The other vector
     * @return the distance
     */
    public double distanceSquared(Vector3D o) {
        return square(x - o.x) + square(y - o.y) + square(z - o.z);
    }

    /**
     * Gets the angle between this vector and another in radians.
     *
     * @param other The other vector
     * @return angle in radians
     */
    public float angle(Vector3D other) {
        double dot = this.dot(other) / (this.length() * other.length());
        return (float)Math.acos(dot);
     }


    /**
     * Sets this vector to the midpoint between this vector and another.
     *
     * @param other The other vector
     * @return this same vector (now a midpoint)
     */
    
    public Vector3D midpoint(Vector3D other) {
        x = (x + other.x) / 2;
        y = (y + other.y) / 2;
        z = (z + other.z) / 2;
        return this;
    }

    /**
     * Gets a new midpoint vector between this vector and another.
     *
     * @param other The other vector
     * @return a new midpoint vector
     */
    
    public Vector3D getMidpoint(Vector3D other) {
        double x = (this.x + other.x) / 2;
        double y = (this.y + other.y) / 2;
        double z = (this.z + other.z) / 2;
        return new Vector3D(x, y, z);
    }

    /**
     * Performs scalar multiplication, multiplying all components with a
     * scalar.
     *
     * @param m The factor
     * @return the same vector
     */
    
    public Vector3D multiply(int m) {
        x *= m;
        y *= m;
        z *= m;
        return this;
    }

    /**
     * Performs scalar multiplication, multiplying all components with a
     * scalar.
     *
     * @param m The factor
     * @return the same vector
     */
    
    public Vector3D multiply(double m) {
        x *= m;
        y *= m;
        z *= m;
        return this;
    }

    /**
     * Performs scalar multiplication, multiplying all components with a
     * scalar.
     *
     * @param m The factor
     * @return the same vector
     */
    
    public Vector3D multiply(float m) {
        x *= m;
        y *= m;
        z *= m;
        return this;
    }

    /**
     * Calculates the dot product of this vector with another. The dot product
     * is defined as x1*x2+y1*y2+z1*z2. The returned value is a scalar.
     *
     * @param other The other vector
     * @return dot product
     */
    public double dot(Vector3D other) {
        return x * other.x + y * other.y + z * other.z;
    }

    /**
     * Calculates the cross product of this vector with another. The cross
     * product is defined as:
     * <ul>
     * <li>x = y1 * z2 - y2 * z1
     * <li>y = z1 * x2 - z2 * x1
     * <li>z = x1 * y2 - x2 * y1
     * </ul>
     *
     * @param o The other vector
     * @return the same vector
     */
    
    public Vector3D crossProduct(Vector3D o) {
        double newX = y * o.z - o.y * z;
        double newY = z * o.x - o.z * x;
        double newZ = x * o.y - o.x * y;

        x = newX;
        y = newY;
        z = newZ;
        return this;
    }

    /**
     * Calculates the cross product of this vector with another without mutating
     * the original. The cross product is defined as:
     * <ul>
     * <li>x = y1 * z2 - y2 * z1
     * <li>y = z1 * x2 - z2 * x1
     * <li>z = x1 * y2 - x2 * y1
     * </ul>
     *
     * @param o The other vector
     * @return a new vector
     */
    
    public Vector3D getCrossProduct(Vector3D o) {
        double x = this.y * o.z - o.y * this.z;
        double y = this.z * o.x - o.z * this.x;
        double z = this.x * o.y - o.x * this.y;
        return new Vector3D(x, y, z);
    }

    /**
     * Converts this vector to a unit vector (a vector with length of 1).
     *
     * @return the same vector
     */
    
    public Vector3D normalize() {
        double length = length();

        x /= length;
        y /= length;
        z /= length;

        return this;
    }

    /**
     * Zero this vector's components.
     *
     * @return the same vector
     */
    
    public Vector3D zero() {
        x = 0;
        y = 0;
        z = 0;
        return this;
    }

    /**
     * Converts each component of value <code>-0.0</code> to <code>0.0</code>.
     *
     * @return This vector.
     */
    
    public Vector3D normalizeZeros() {
        if (x == -0.0D) x = 0.0D;
        if (y == -0.0D) y = 0.0D;
        if (z == -0.0D) z = 0.0D;
        return this;
    }

    /**
     * Returns whether this vector is in an axis-aligned bounding box.
     * <p>
     * The minimum and maximum vectors given must be truly the minimum and
     * maximum X, Y and Z components.
     *
     * @param min Minimum vector
     * @param max Maximum vector
     * @return whether this vector is in the AABB
     */
    public boolean isInAABB(Vector3D min, Vector3D max) {
        return x >= min.x && x <= max.x && y >= min.y && y <= max.y && z >= min.z && z <= max.z;
    }

    /**
     * Returns whether this vector is within a sphere.
     *
     * @param origin Sphere origin.
     * @param radius Sphere radius
     * @return whether this vector is in the sphere
     */
    public boolean isInSphere(Vector3D origin, double radius) {
        return (square(origin.x - x) + square(origin.y - y) + square(origin.z - z)) <= square(radius);
    }

    /**
     * Returns if a vector is normalized
     *
     * @return whether the vector is normalised
     */
    public boolean isNormalized() {
        return Math.abs(this.lengthSquared() - 1) < getEpsilon();
    }

    /**
     * Rotates the vector around the x-axis.
     * <p>
     * This piece of math is based on the standard rotation matrix for vectors
     * in three-dimensional space. This matrix can be found here:
     * <a href="https://en.wikipedia.org/wiki/Rotation_matrix#Basic_rotations">Rotation
     * Matrix</a>.
     *
     * @param angle the angle to rotate the vector about. This angle is passed
     * in radians
     * @return the same vector
     */
    
    public Vector3D rotateAroundX(double angle) {
        double angleCos = Math.cos(angle);
        double angleSin = Math.sin(angle);

        double y = angleCos * getY() - angleSin * getZ();
        double z = angleSin * getY() + angleCos * getZ();
        return setY(y).setZ(z);
    }

    /**
     * Rotates the vector around the y-axis.
     * <p>
     * This piece of math is based on the standard rotation matrix for vectors
     * in three-dimensional space. This matrix can be found here:
     * <a href="https://en.wikipedia.org/wiki/Rotation_matrix#Basic_rotations">Rotation
     * Matrix</a>.
     *
     * @param angle the angle to rotate the vector about. This angle is passed
     * in radians
     * @return the same vector
     */
    
    public Vector3D rotateAroundY(double angle) {
        double angleCos = Math.cos(angle);
        double angleSin = Math.sin(angle);

        double x = angleCos * getX() + angleSin * getZ();
        double z = -angleSin * getX() + angleCos * getZ();
        return setX(x).setZ(z);
    }

    /**
     * Rotates the vector around the z axis
     * <p>
     * This piece of math is based on the standard rotation matrix for vectors
     * in three dimensional space. This matrix can be found here:
     * <a href="https://en.wikipedia.org/wiki/Rotation_matrix#Basic_rotations">Rotation
     * Matrix</a>.
     *
     * @param angle the angle to rotate the vector about. This angle is passed
     * in radians
     * @return the same vector
     */
    
    public Vector3D rotateAroundZ(double angle) {
        double angleCos = Math.cos(angle);
        double angleSin = Math.sin(angle);

        double x = angleCos * getX() - angleSin * getY();
        double y = angleSin * getX() + angleCos * getY();
        return setX(x).setY(y);
    }

    /**
     * Rotates the vector around a given arbitrary axis in 3 dimensional space.
     *
     * <p>
     * Rotation will follow the general Right-Hand-Rule, which means rotation
     * will be counterclockwise when the axis is pointing towards the observer.
     * <p>
     * This method will always make sure the provided axis is a unit vector, to
     * not modify the length of the vector when rotating. If you are experienced
     * with the scaling of a non-unit axis vector, you can use
     * {@link Vector3D#rotateAroundNonUnitAxis(Vector3D, double)}.
     *
     * @param axis the axis to rotate the vector around. If the passed vector is
     * not of length 1, it gets copied and normalized before using it for the
     * rotation. Please use {@link Vector3D#normalize()} on the instance before
     * passing it to this method
     * @param angle the angle to rotate the vector around the axis
     * @return the same vector
     * @throws IllegalArgumentException if the provided axis vector instance is
     * null
     */
    
    public Vector3D rotateAroundAxis(Vector3D axis, double angle) throws IllegalArgumentException {
        Preconditions.checkArgument(axis != null, "The provided axis vector was null");

        return rotateAroundNonUnitAxis(axis.isNormalized() ? axis : axis.clone().normalize(), angle);
    }

    /**
     * Rotates the vector around a given arbitrary axis in 3 dimensional space.
     *
     * <p>
     * Rotation will follow the general Right-Hand-Rule, which means rotation
     * will be counterclockwise when the axis is pointing towards the observer.
     * <p>
     * Note that the vector length will change accordingly to the axis vector
     * length. If the provided axis is not a unit vector, the rotated vector
     * will not have its previous length. The scaled length of the resulting
     * vector will be related to the axis vector. If you are not perfectly sure
     * about the scaling of the vector, use
     * {@link Vector3D#rotateAroundAxis(Vector3D, double)}
     *
     * @param axis the axis to rotate the vector around.
     * @param angle the angle to rotate the vector around the axis
     * @return the same vector
     * @throws IllegalArgumentException if the provided axis vector instance is
     * null
     */
    
    public Vector3D rotateAroundNonUnitAxis(Vector3D axis, double angle) throws IllegalArgumentException {
        Preconditions.checkArgument(axis != null, "The provided axis vector was null");

        double x = getX(), y = getY(), z = getZ();
        double x2 = axis.getX(), y2 = axis.getY(), z2 = axis.getZ();

        double cosTheta = Math.cos(angle);
        double sinTheta = Math.sin(angle);
        double dotProduct = this.dot(axis);

        double xPrime = x2 * dotProduct * (1d - cosTheta)
                + x * cosTheta
                + (-z2 * y + y2 * z) * sinTheta;
        double yPrime = y2 * dotProduct * (1d - cosTheta)
                + y * cosTheta
                + (z2 * x - x2 * z) * sinTheta;
        double zPrime = z2 * dotProduct * (1d - cosTheta)
                + z * cosTheta
                + (-y2 * x + x2 * y) * sinTheta;

        return setX(xPrime).setY(yPrime).setZ(zPrime);
    }

    /**
     * Gets the X component.
     *
     * @return The X component.
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the floored value of the X component, indicating the block that
     * this vector is contained with.
     *
     * @return block X
     */
    public int getBlockX() {
        return floor(x);
    }

    /**
     * Gets the Y component.
     *
     * @return The Y component.
     */
    public double getY() {
        return y;
    }

    /**
     * Gets the floored value of the Y component, indicating the block that
     * this vector is contained with.
     *
     * @return block y
     */
    public int getBlockY() {
        return floor(y);
    }

    /**
     * Gets the Z component.
     *
     * @return The Z component.
     */
    public double getZ() {
        return z;
    }

    /**
     * Gets the floored value of the Z component, indicating the block that
     * this vector is contained with.
     *
     * @return block z
     */
    public int getBlockZ() {
        return floor(z);
    }

    /**
     * Set the X component.
     *
     * @param x The new X component.
     * @return This vector.
     */
    
    public Vector3D setX(int x) {
        this.x = x;
        return this;
    }

    /**
     * Set the X component.
     *
     * @param x The new X component.
     * @return This vector.
     */
    
    public Vector3D setX(double x) {
        this.x = x;
        return this;
    }

    /**
     * Set the X component.
     *
     * @param x The new X component.
     * @return This vector.
     */
    
    public Vector3D setX(float x) {
        this.x = x;
        return this;
    }

    /**
     * Set the Y component.
     *
     * @param y The new Y component.
     * @return This vector.
     */
    
    public Vector3D setY(int y) {
        this.y = y;
        return this;
    }

    /**
     * Set the Y component.
     *
     * @param y The new Y component.
     * @return This vector.
     */
    
    public Vector3D setY(double y) {
        this.y = y;
        return this;
    }

    /**
     * Set the Y component.
     *
     * @param y The new Y component.
     * @return This vector.
     */
    
    public Vector3D setY(float y) {
        this.y = y;
        return this;
    }

    /**
     * Set the Z component.
     *
     * @param z The new Z component.
     * @return This vector.
     */
    
    public Vector3D setZ(int z) {
        this.z = z;
        return this;
    }

    /**
     * Set the Z component.
     *
     * @param z The new Z component.
     * @return This vector.
     */
    
    public Vector3D setZ(double z) {
        this.z = z;
        return this;
    }

    /**
     * Set the Z component.
     *
     * @param z The new Z component.
     * @return This vector.
     */
    
    public Vector3D setZ(float z) {
        this.z = z;
        return this;
    }

    /**
     * Checks to see if two objects are equal.
     * <p>
     * Only two Vectors can ever return true. This method uses a fuzzy match
     * to account for floating point errors. The epsilon can be retrieved
     * with epsilon.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector3D)) {
            return false;
        }

        Vector3D other = (Vector3D) obj;

        return Math.abs(x - other.x) < epsilon && Math.abs(y - other.y) < epsilon && Math.abs(z - other.z) < epsilon && (this.getClass().equals(obj.getClass()));
    }

    /**
     * Returns a hash code for this vector
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        int hash = 7;

        hash = 79 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.z) ^ (Double.doubleToLongBits(this.z) >>> 32));
        return hash;
    }

    /**
     * Get a new vector.
     *
     * @return vector
     */
    
    @Override
    public Vector3D clone() {
        try {
            return (Vector3D) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error(e);
        }
    }

    /**
     * Returns this vector's components as x,y,z.
     */
    @Override
    public String toString() {
        return "Vector3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    /**
     * Check if each component of this Vector is finite.
     *
     * @throws IllegalArgumentException if any component is not finite
     */
    public void checkFinite() throws IllegalArgumentException {
        checkFinite(x, "x not finite");
        checkFinite(y, "y not finite");
        checkFinite(z, "z not finite");
    }

    /**
     * Get the threshold used for equals().
     *
     * @return The epsilon.
     */
    public static double getEpsilon() {
        return epsilon;
    }

    /**
     * Gets the minimum components of two vectors.
     *
     * @param v1 The first vector.
     * @param v2 The second vector.
     * @return minimum
     */
    
    public static Vector3D getMinimum(Vector3D v1, Vector3D v2) {
        return new Vector3D(Math.min(v1.x, v2.x), Math.min(v1.y, v2.y), Math.min(v1.z, v2.z));
    }

    /**
     * Gets the maximum components of two vectors.
     *
     * @param v1 The first vector.
     * @param v2 The second vector.
     * @return maximum
     */
    
    public static Vector3D getMaximum(Vector3D v1, Vector3D v2) {
        return new Vector3D(Math.max(v1.x, v2.x), Math.max(v1.y, v2.y), Math.max(v1.z, v2.z));
    }

    /**
     * Gets a random vector with components having a random value between 0
     * and 1.
     *
     * @return A random vector.
     */
    
    public Vector3D getRandom() {
        return new Vector3D(random.nextDouble(), random.nextDouble(), random.nextDouble());
    }

    
    public Vector3D deserialize(Map<String, Object> args) {
        double x = 0;
        double y = 0;
        double z = 0;

        if (args.containsKey("x")) {
            x = (Double) args.get("x");
        }
        if (args.containsKey("y")) {
            y = (Double) args.get("y");
        }
        if (args.containsKey("z")) {
            z = (Double) args.get("z");
        }

        return new Vector3D(x, y, z);
    }
    
    
	public int floor(double num) {
		int floor = (int) num;
		return floor == num ? floor : floor - (int) (Double.doubleToRawLongBits(num) >>> 63);
	}

	public int ceil(double num) {
		int floor = (int) num;
		return (double) floor == num ? floor : floor + (int) (~Double.doubleToRawLongBits(num) >>> 63);
	}

	public int round(double num) {
		return floor(num + 0.5D);
	}

	public double square(double num) {
		return num * num;
	}

	public int toInt(Object object) {
		if ((object instanceof Number)) {
			return ((Number) object).intValue();
		}
		try {
			return Integer.parseInt(object.toString());
		} catch (NumberFormatException | NullPointerException ex) {
		}
        return 0;
	}

	public float toFloat(Object object) {
		if ((object instanceof Number)) {
			return ((Number) object).floatValue();
		}
		try {
			return Float.parseFloat(object.toString());
		} catch (NumberFormatException | NullPointerException ex) {
		}
        return 0.0F;
	}

	public double toDouble(Object object) {
		if ((object instanceof Number)) {
			return ((Number) object).doubleValue();
		}
		try {
			return Double.parseDouble(object.toString());
		} catch (NumberFormatException | NullPointerException ex) {
		}
		return 0.0D;
	}

	public long toLong(Object object) {
		if ((object instanceof Number)) {
			return ((Number) object).longValue();
		}
		try {
			return Long.parseLong(object.toString());
		} catch (NumberFormatException | NullPointerException ex) {
		}
		return 0L;
	}

	public short toShort(Object object) {
		if ((object instanceof Number)) {
			return ((Number) object).shortValue();
		}
		try {
			return Short.parseShort(object.toString());
		} catch (NumberFormatException | NullPointerException ex) {
		}
		return 0;
	}

	public byte toByte(Object object) {
		if ((object instanceof Number)) {
			return ((Number) object).byteValue();
		}
		try {
			return Byte.parseByte(object.toString());
		} catch (NumberFormatException | NullPointerException ex) {
		}
		return 0;
	}

	public boolean isFinite(double d) {
		return Math.abs(d) <= Double.MAX_VALUE;
	}

	public boolean isFinite(float f) {
		return Math.abs(f) <= Float.MAX_VALUE;
	}

	public void checkFinite(double d, String message) {
		if (!isFinite(d)) {
			throw new IllegalArgumentException(message);
		}
	}

	public void checkFinite(float d, String message) {
		if (!isFinite(d)) {
			throw new IllegalArgumentException(message);
		}
	}

    public static void getCube(Vector3D v1, Vector3D v2, Consumer<Vector3D> action) {
        final Vector3D max = getMaximum(v1, v2);
        final Vector3D min = getMinimum(v1, v2);

        for(double x = min.getX(); x <= max.getX(); x++) {
            for(double y = min.getY(); y <= max.getY(); y++) {
                for(double z = min.getZ(); z <= max.getZ(); z++) {
                    action.accept(new Vector3D(x, y, z));
                }
            }
        }

    }

    public static List<Vector3D> getCube(Vector3D v1, Vector3D v2) {
        final List<Vector3D> cubeVectors = new LinkedList<>();

        final Vector3D max = getMaximum(v1, v2);
        final Vector3D min = getMinimum(v1, v2);

        for(double x = min.getX(); x <= max.getX(); x++) {
            for(double y = min.getY(); y <= max.getY(); y++) {
                for(double z = min.getZ(); z <= max.getZ(); z++) {
                    cubeVectors.add(new Vector3D(x, y, z));
                }
            }
        }

        return cubeVectors;
    }

}