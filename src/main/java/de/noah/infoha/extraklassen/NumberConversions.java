package de.noah.infoha.extraklassen;

public class NumberConversions {

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
}
