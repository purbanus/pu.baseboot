package pu.services;

import java.net.InetAddress;
import java.util.Comparator;

public class InetAddressComparator implements Comparator<InetAddress>
{

public InetAddressComparator()
{
	super();
}

@Override
public int compare( InetAddress adr1, InetAddress adr2 )
{
	byte [] ba1 = adr1.getAddress();
	byte [] ba2 = adr2.getAddress();

	// general ordering: ipv4 before ipv6
	if ( ba1.length < ba2.length )
	{
		return -1;
	}
	if ( ba1.length > ba2.length )
	{
		return 1;
	}

	// we have 2 ips of the same type, so we have to compare each byte
	for ( int i = 0; i < ba1.length; i++ )
	{
		int b1 = unsignedByteToInt( ba1[i] );
		int b2 = unsignedByteToInt( ba2[i] );
		if ( b1 == b2 )
		{
			continue;
		}
		if ( b1 < b2 )
		{
			return -1;
		}
		else
		{
			return 1;
		}
	}
	return 0;
}

private int unsignedByteToInt( byte b )
{
	return b & 0xFF;
}
}
