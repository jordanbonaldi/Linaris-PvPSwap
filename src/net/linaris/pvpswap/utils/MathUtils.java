package net.linaris.pvpswap.utils;



import java.util.Random;


public class MathUtils {
    static public float nanoToSec = 1 / 1000000000f;

    // ---
    static public float FLOAT_ROUNDING_ERROR = 0.000001f; // 32 bits
    static public float PI = 3.1415927f;
    static public float PI2 = MathUtils.PI * 2;

    static public float SQRT_3 = 1.73205f;

    static public float E = 2.7182818f;

    static private int SIN_BITS = 14; // 16KB. Adjust for accuracy.
    static private int SIN_MASK = ~(-1 << MathUtils.SIN_BITS);
    static private int SIN_COUNT = MathUtils.SIN_MASK + 1;

    static private float radFull = MathUtils.PI * 2;
    static private float degFull = 360;
    static private float radToIndex = MathUtils.SIN_COUNT / MathUtils.radFull;
    static private float degToIndex = MathUtils.SIN_COUNT / MathUtils.degFull;

    
    static public float radiansToDegrees = 180f / MathUtils.PI;
    static public float radDeg = MathUtils.radiansToDegrees;
    
    static public float degreesToRadians = MathUtils.PI / 180;
    static public float degRad = MathUtils.degreesToRadians;

    static private class Sin {
        static float[] table = new float[MathUtils.SIN_COUNT];
        static {
            for (int i = 0; i < MathUtils.SIN_COUNT; i++) {
                Sin.table[i] = (float) Math.sin((i + 0.5f) / MathUtils.SIN_COUNT * MathUtils.radFull);
            }
            for (int i = 0; i < 360; i += 90) {
                Sin.table[(int) (i * MathUtils.degToIndex) & MathUtils.SIN_MASK] = (float) Math.sin(i * MathUtils.degreesToRadians);
            }
        }
    }

    
    static public float sin(float radians) {
        return Sin.table[(int) (radians * MathUtils.radToIndex) & MathUtils.SIN_MASK];
    }

    
    static public float cos(float radians) {
        return Sin.table[(int) ((radians + MathUtils.PI / 2) * MathUtils.radToIndex) & MathUtils.SIN_MASK];
    }

    
    static public float sinDeg(float degrees) {
        return Sin.table[(int) (degrees * MathUtils.degToIndex) & MathUtils.SIN_MASK];
    }

    
    static public float cosDeg(float degrees) {
        return Sin.table[(int) ((degrees + 90) * MathUtils.degToIndex) & MathUtils.SIN_MASK];
    }

    // ---

    static private int ATAN2_BITS = 7; // Adjust for accuracy.
    static private int ATAN2_BITS2 = MathUtils.ATAN2_BITS << 1;
    static private int ATAN2_MASK = ~(-1 << MathUtils.ATAN2_BITS2);
    static private int ATAN2_COUNT = MathUtils.ATAN2_MASK + 1;
    static int ATAN2_DIM = (int) Math.sqrt(MathUtils.ATAN2_COUNT);
    static private float INV_ATAN2_DIM_MINUS_1 = 1.0f / (MathUtils.ATAN2_DIM - 1);

    static private class Atan2 {
        static float[] table = new float[MathUtils.ATAN2_COUNT];
        static {
            for (int i = 0; i < MathUtils.ATAN2_DIM; i++) {
                for (int j = 0; j < MathUtils.ATAN2_DIM; j++) {
                    float x0 = (float) i / MathUtils.ATAN2_DIM;
                    float y0 = (float) j / MathUtils.ATAN2_DIM;
                    Atan2.table[j * MathUtils.ATAN2_DIM + i] = (float) Math.atan2(y0, x0);
                }
            }
        }
    }

    
    static public float atan2(float y, float x) {
        float add, mul;
        if (x < 0) {
            if (y < 0) {
                y = -y;
                mul = 1;
            } else {
                mul = -1;
            }
            x = -x;
            add = -MathUtils.PI;
        } else {
            if (y < 0) {
                y = -y;
                mul = -1;
            } else {
                mul = 1;
            }
            add = 0;
        }
        float invDiv = 1 / ((x < y ? y : x) * MathUtils.INV_ATAN2_DIM_MINUS_1);

        if (invDiv == Float.POSITIVE_INFINITY) { return ((float) Math.atan2(y, x) + add) * mul; }

        int xi = (int) (x * invDiv);
        int yi = (int) (y * invDiv);
        return (Atan2.table[yi * MathUtils.ATAN2_DIM + xi] + add) * mul;
    }

    // ---

    static public Random random = new Random();

    
    static public int random(int range) {
        return MathUtils.random.nextInt(range + 1);
    }

    
    static public int random(int start, int end) {
        return start + MathUtils.random.nextInt(end - start + 1);
    }

    
    static public boolean randomBoolean() {
        return MathUtils.random.nextBoolean();
    }

    
    static public boolean randomBoolean(float chance) {
        return MathUtils.random() < chance;
    }

    
    static public float random() {
        return MathUtils.random.nextFloat();
    }

    
    static public float random(float range) {
        return MathUtils.random.nextFloat() * range;
    }

    
    static public float random(float start, float end) {
        return start + MathUtils.random.nextFloat() * (end - start);
    }

    // ---

    
    static public int nextPowerOfTwo(int value) {
        if (value == 0) { return 1; }
        value--;
        value |= value >> 1;
        value |= value >> 2;
        value |= value >> 4;
        value |= value >> 8;
        value |= value >> 16;
        return value + 1;
    }

    static public boolean isPowerOfTwo(int value) {
        return value != 0 && (value & value - 1) == 0;
    }

    // ---

    static public int clamp(int value, int min, int max) {
        if (value < min) { return min; }
        if (value > max) { return max; }
        return value;
    }

    static public short clamp(short value, short min, short max) {
        if (value < min) { return min; }
        if (value > max) { return max; }
        return value;
    }

    static public float clamp(float value, float min, float max) {
        if (value < min) { return min; }
        if (value > max) { return max; }
        return value;
    }

    // ---

    static private int BIG_ENOUGH_INT = 16 * 1024;
    static private double BIG_ENOUGH_FLOOR = MathUtils.BIG_ENOUGH_INT;
    static private double CEIL = 0.9999999;
    // static private double BIG_ENOUGH_CEIL = NumberUtils
    // .longBitsToDouble(NumberUtils.doubleToLongBits(BIG_ENOUGH_INT + 1) - 1);
    static private double BIG_ENOUGH_CEIL = 16384.999999999996;
    static private double BIG_ENOUGH_ROUND = MathUtils.BIG_ENOUGH_INT + 0.5f;

    
    static public int floor(float x) {
        return (int) (x + MathUtils.BIG_ENOUGH_FLOOR) - MathUtils.BIG_ENOUGH_INT;
    }

    
    static public int floorPositive(float x) {
        return (int) x;
    }

    
    static public int ceil(float x) {
        return (int) (x + MathUtils.BIG_ENOUGH_CEIL) - MathUtils.BIG_ENOUGH_INT;
    }

    
    static public int ceilPositive(float x) {
        return (int) (x + MathUtils.CEIL);
    }

    
    static public int round(float x) {
        return (int) (x + MathUtils.BIG_ENOUGH_ROUND) - MathUtils.BIG_ENOUGH_INT;
    }

    
    static public int roundPositive(float x) {
        return (int) (x + 0.5f);
    }

    
    static public boolean isZero(float value) {
        return Math.abs(value) <= MathUtils.FLOAT_ROUNDING_ERROR;
    }

    
    static public boolean isZero(float value, float tolerance) {
        return Math.abs(value) <= tolerance;
    }

    
    static public boolean isEqual(float a, float b) {
        return Math.abs(a - b) <= MathUtils.FLOAT_ROUNDING_ERROR;
    }

    
    static public boolean isEqual(float a, float b, float tolerance) {
        return Math.abs(a - b) <= tolerance;
    }
}
