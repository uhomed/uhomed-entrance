//package com.uhomed.entrance.core.utils;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.io.PrintStream;
//import java.lang.instrument.Instrumentation;
//import java.lang.reflect.Field;
//import java.lang.reflect.Modifier;
//import java.util.IdentityHashMap;
//import java.util.Map;
//
//public class SizeOf {
//	private static OutputStream		out						= System.out;
//	private static Instrumentation	inst;
//	private static long				MIN_CLASS_SIZE_TO_LOG	= 1048576L;
//	private static boolean			SKIP_STATIC_FIELD		= false;
//	private static boolean			SKIP_FINAL_FIELD		= false;
//	private static boolean			SKIP_FLYWEIGHT_FIELD	= false;
//	private static boolean			debug					= false;
//	private static String[]			unit					= { "b", "Kb", "Mb" };
//
//	public static void premain(String paramString, Instrumentation paramInstrumentation) {
//		inst = paramInstrumentation;
//		System.out.println( "JAVAGENT: call premain instrumentation for class SizeOf" );
//	}
//
//	public static long sizeOf(Object paramObject) {
//		if (inst == null) {
//			throw new IllegalStateException( "Instrumentation is null" );
//		}
//		if ((SKIP_FLYWEIGHT_FIELD) && (isSharedFlyweight( paramObject ))) {
//			return 0L;
//		}
//		return inst.getObjectSize( paramObject );
//	}
//
//	public static String humanReadable(long paramLong) {
//		double d = paramLong;
//		for (int i = 0; (i < 3) && (d >= 1024.0D); i++) {
//			d /= 1024.0D;
//		}
//		return d + unit[ i ];
//	}
//
//	public static long deepSizeOf(Object paramObject) {
//		IdentityHashMap localIdentityHashMap = new IdentityHashMap();
//		return deepSizeOf( paramObject, localIdentityHashMap, 0 );
//	}
//
//	/**
//	 * @deprecated
//	 */
//	public static long iterativeSizeOf(Object paramObject)
//			throws IllegalArgumentException, IllegalAccessException, IOException {
//		return deepSizeOf( paramObject );
//	}
//
//	private static String indent(int paramInt) {
//		StringBuilder localStringBuilder = new StringBuilder();
//		for (int i = 0; i < paramInt; i++) {
//			localStringBuilder.append( "  " );
//		}
//		return localStringBuilder.toString();
//	}
//
//	private static long deepSizeOf(Object paramObject, Map<Object, Object> paramMap, int paramInt)
//	{
//		if (paramObject == null)
//		{
//			if (debug) {
//				print("null\n");
//			}
//			return 0L;
//		}
//		long l = 0L;
//		if (paramMap.containsKey(paramObject))
//		{
//			if (debug) {
//				print("\n%s{ yet computed }\n", new Object[] { indent(paramInt) });
//			}
//			return 0L;
//		}
//		if (debug) {
//			print("\n%s{ %s\n", new Object[] { indent(paramInt), paramObject.getClass().getName() });
//		}
//		paramMap.put(paramObject, null);
//		l = sizeOf(paramObject);
//		Object localObject2;
//		if ((paramObject instanceof Object[]))
//		{
//			int i = 0;
//			for (localObject2 : (Object[])paramObject)
//			{
//				if (debug) {
//					print("%s [%d] = ", new Object[] { indent(paramInt), Integer.valueOf(i++) });
//				}
//				l += deepSizeOf(localObject2, paramMap, paramInt + 1);
//			}
//		}
//		else
//		{
//			Field[] arrayOfField = paramObject.getClass().getDeclaredFields();
//			for (localObject2 : arrayOfField)
//			{
//				((Field)localObject2).setAccessible(true);
//				Object localObject3;
//				try
//				{
//					localObject3 = ((Field)localObject2).get(paramObject);
//				}
//				catch (IllegalArgumentException localIllegalArgumentException)
//				{
//					throw new RuntimeException(localIllegalArgumentException);
//				}
//				catch (IllegalAccessException localIllegalAccessException)
//				{
//					throw new RuntimeException(localIllegalAccessException);
//				}
//				if (isComputable((Field)localObject2))
//				{
//					if (debug) {
//						print("%s %s = ", new Object[] { indent(paramInt), ((Field)localObject2).getName() });
//					}
//					l += deepSizeOf(localObject3, paramMap, paramInt + 1);
//				}
//				else if (debug)
//				{
//					print("%s %s = %s\n", new Object[] { indent(paramInt), ((Field)localObject2).getName(), localObject3.toString() });
//				}
//			}
//		}
//		if (debug) {
//			print("%s} size = %s\n", new Object[] { indent(paramInt), humanReadable(l) });
//		}
//		if ((MIN_CLASS_SIZE_TO_LOG > 0L) && (l >= MIN_CLASS_SIZE_TO_LOG)) {
//			print("Found big object: %s%s@%s size: %s\n", new Object[] { indent(paramInt), paramObject.getClass().getName(), Integer.valueOf(System.identityHashCode(paramObject)), humanReadable(l) });
//		}
//		return l;
//	}
//
//	private static boolean isAPrimitiveType(Class paramClass) {
//		if (paramClass == Boolean.TYPE) {
//			return true;
//		}
//		if (paramClass == Character.TYPE) {
//			return true;
//		}
//		if (paramClass == Byte.TYPE) {
//			return true;
//		}
//		if (paramClass == Short.TYPE) {
//			return true;
//		}
//		if (paramClass == Integer.TYPE) {
//			return true;
//		}
//		if (paramClass == Long.TYPE) {
//			return true;
//		}
//		if (paramClass == Float.TYPE) {
//			return true;
//		}
//		if (paramClass == Double.TYPE) {
//			return true;
//		}
//		return paramClass == Void.TYPE;
//	}
//
//	private static boolean isComputable(Field paramField) {
//		int i = paramField.getModifiers();
//		if (isAPrimitiveType( paramField.getType() )) {
//			return false;
//		}
//		if ((SKIP_STATIC_FIELD) && (Modifier.isStatic( i ))) {
//			return false;
//		}
//		return (!SKIP_FINAL_FIELD) || (!Modifier.isFinal( i ));
//	}
//
//	private static boolean isSharedFlyweight(Object paramObject) {
//		if ((paramObject instanceof Comparable)) {
//			if ((paramObject instanceof Enum)) {
//				return true;
//			}
//			if ((paramObject instanceof String)) {
//				return paramObject == ((String) paramObject).intern();
//			}
//			if ((paramObject instanceof Boolean)) {
//				return (paramObject == Boolean.TRUE) || (paramObject == Boolean.FALSE);
//			}
//			if ((paramObject instanceof Integer)) {
//				return paramObject == Integer.valueOf( ((Integer) paramObject).intValue() );
//			}
//			if ((paramObject instanceof Short)) {
//				return paramObject == Short.valueOf( ((Short) paramObject).shortValue() );
//			}
//			if ((paramObject instanceof Byte)) {
//				return paramObject == Byte.valueOf( ((Byte) paramObject).byteValue() );
//			}
//			if ((paramObject instanceof Long)) {
//				return paramObject == Long.valueOf( ((Long) paramObject).longValue() );
//			}
//			if ((paramObject instanceof Character)) {
//				return paramObject == Character.valueOf( ((Character) paramObject).charValue() );
//			}
//		}
//		return false;
//	}
//
//	public static void setMinSizeToLog(long paramLong) {
//		MIN_CLASS_SIZE_TO_LOG = paramLong;
//	}
//
//	public static void skipFinalField(boolean paramBoolean) {
//		SKIP_FINAL_FIELD = paramBoolean;
//	}
//
//	public static void skipStaticField(boolean paramBoolean) {
//		SKIP_STATIC_FIELD = paramBoolean;
//	}
//
//	public static void skipFlyweightObject(boolean paramBoolean) {
//		SKIP_FLYWEIGHT_FIELD = paramBoolean;
//	}
//
//	private static void print(String paramString) {
//		try {
//			out.write( paramString.getBytes() );
//		} catch (IOException localIOException) {
//			throw new RuntimeException( localIOException );
//		}
//	}
//
//	private static void print(String paramString, Object... paramVarArgs) {
//		try {
//			out.write( String.format( paramString, paramVarArgs ).getBytes() );
//		} catch (IOException localIOException) {
//			throw new RuntimeException( localIOException );
//		}
//	}
//
//	public static void setLogOutputStream(OutputStream paramOutputStream) {
//		if (paramOutputStream == null) {
//			throw new IllegalArgumentException( "Can't use a null OutputStream" );
//		}
//		out = paramOutputStream;
//	}
//
//	public static void turnOnDebug() {
//		debug = true;
//	}
//
//	public static void turnOffDebug() {
//		debug = false;
//	}
//}
