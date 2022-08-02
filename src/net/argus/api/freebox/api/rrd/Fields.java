package net.argus.api.freebox.api.rrd;

public class Fields {
	
	private Fields() {}
	
	public class NET {
		public static final String BW_UP = "bw_up";
		public static final String BW_DOWN = "bw_down";
		public static final String RATE_UP = "rate_up";
		public static final String RATE_DOWN = "rate_down";
		public static final String VPN_RATE_UP = "vpn_rate_up";
		public static final String VPN_RATE_DOWN = "vpn_rate_down";
	}
	
	public class TEMP {
		public static final String CPUM = "cpum";
		public static final String CPUB = "cpub";
		public static final String SW = "sw";
		public static final String HDD = "hdd";
		public static final String FAN_SPEED = "fan_speed";
		public static final String TEMP1 = "temp1";
		public static final String TEMP2 = "temp2";
		public static final String TEMP3 = "temp3";
	}
	
	public class DSL {
		public static final String RATE_UP = "rate_up";
		public static final String RATE_DOWN = "rate_down";
		public static final String SNR_UP = "snr_up";
		public static final String SNR_DOWN = "snr_down";
	}
	
	public class SWITCH {
		public static final String RX_1 = "rx_1";
		public static final String TX_1 = "tx_1";
		public static final String RX_2 = "rx_2";
		public static final String TX_2 = "tx_2";
		public static final String RX_3 = "rx_3";
		public static final String TX_3 = "tx_3";
		public static final String RX_4 = "rx_4";
		public static final String TX_4 = "tx_4";
		
	}

}
