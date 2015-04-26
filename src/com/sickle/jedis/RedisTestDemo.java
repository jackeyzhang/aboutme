package com.sickle.jedis;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;


public class RedisTestDemo
{

	
	/**
	 * @param args
	 * @throws RedisException 
	 */
	public static void main( String[] args ) 
	{
		
		Jedis jr = new Jedis("192.168.218.118");
		jr.connect( );
		
		/**
		 * All demo
		 */
		jr.flushDB( );
		
		
		/**
		 * String demo
		 */
		String key = "mkey";
		jr.set( key, "hello redis" );
		
		String v= new String(jr.get( key ));
		String k2 = "count";
		jr.incr( k2 );
		jr.incr( k2 );
		System.out.println( v );
		
		System.out.println(new String(jr.get( k2 )));
		
		System.out.println(jr.exists( key ));
		
		/**
		 * List demo
		 */
		String[] list = { "list1" ,"list2" ,"list3" ,"list4" };
		jr.lpush( "listkey", list );
		System.out.println( jr.type( "listkey" ));
		System.out.println( jr.lpop( "listkey" ));
		System.out.println( jr.lpop( "listkey" ));
		System.out.println( jr.rpop( "listkey" ));
		
		/**
		 * Set demo
		 */
		String[] set = { "set1" ,"set2" ,"set3" ,"set4" };
		jr.sadd( "setkey", set );
		System.out.println( jr.type( "setkey" ));
		System.out.println( jr.spop( "setkey" ));
		System.out.println( jr.spop( "setkey" ));
		
		/**
		 * Zset demo
		 */
		jr.expire( "count", 10 );
		
		try
		{
			Thread.sleep(  11 * 1000 );
		}
		catch ( InterruptedException e )
		{
			e.printStackTrace();
		}
		/**
		 * Hash demo
		 */
		System.out.println( jr.keys( "*" )); 
		System.out.println( jr.dbSize( ));
		
		/**
		 * publish/subscribe
		 */
		JedisPubSub pub1 = new JedisPubSub( ) {
			
			@Override
			public void onUnsubscribe( String arg0, int arg1 )
			{
				System.out.println("not get");
			}
			
			@Override
			public void onSubscribe( String arg0, int arg1 )
			{
				System.out.println("get onSubscribe:" + arg0 + "," + arg1 );
			}
			
			@Override
			public void onPUnsubscribe( String arg0, int arg1 )
			{
				System.out.println("on onPUnsubscribe:" + arg0);				
			}
			
			@Override
			public void onPSubscribe( String arg0, int arg1 )
			{
				System.out.println("on onPSubscribe:" + arg0);				
			}
			
			@Override
			public void onPMessage( String arg0, String arg1, String arg2 )
			{
				System.out.println("on onPMessage:" + arg0);				
			}
			
			@Override
			public void onMessage( String arg0, String arg1 )
			{
				System.out.println("on message:" + arg0);				
			}
		};
		jr.subscribe( pub1, "123" );
		
		jr.publish( "123", "send a message to 123" );
	}
}
