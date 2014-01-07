/**
 * 
 */
package com.sickle.jedis;


/**
 * @author chenhao
 *
 */
public class Jedis implements Ijedis<Jedis>
{
	
	public String host = "127.0.0.1";
	
	public Jedis()
	{
	}
	
	public Jedis(String host)
	{
		this.host = host;
	}

	@Override
	public Jedis getJedis( )
	{
		return new Jedis(host);
	}

}
