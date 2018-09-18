package com.http;

public enum CloudVisionMetricUnitEnum {
    AlignmentErrorsRate("alignmentErrorsRate","errors/sec","errors/sec"),
    FcsErrorsRate("fcsErrorsRate","errors/sec","errors/sec"),
    In1024To1522OctetFramesRate("in1024To1522OctetFramesRate","kpps","kilo packets per sec"),
    In128To255OctetFramesRate("in128To255OctetFramesRate","kpps","kilo packets per sec"),
    In1523ToMaxOctetFramesRate("in1523ToMaxOctetFramesRate","kpps","kilo packets per sec"),
    In256To511OctetFramesRate("in256To511OctetFramesRate","kpps","kilo packets per sec"),
    In512To1023OctetFramesRate("in512To1023OctetFramesRate","kpps","kilo packets per sec"),
    In64OctetFramesRate("in64OctetFramesRate","kpps","kilo packets per sec"),
    In65To127OctetFramesRate("in65To127OctetFramesRate","kpps","kilo packets per sec"),
    InBroadcastPktsRate("inBroadcastPktsRate","kpps","kilo packets per sec"),
    InMulticastPktsRate("inMulticastPktsRate","kpps","kilo packets per sec"),
    InDiscardsRate("inDiscardsRate","discards/sec","discards/sec"),
    InErrorsRate("inErrorsRate","errors/sec","errors/sec"),
    InOctetsRate("inOctetsRate","kbps","kilo bits per sec"),
    InUcastPktsRate("inUcastPktsRate","kpps","kilo packets per sec"),
    OutBroadcastPktsRate("outBroadcastPktsRate","kpps","kilo packets per sec"),
    OutDiscardsRate("outDiscardsRate","discards/sec","discards/sec"),
    OutErrorsRate("outErrorsRate","errors/sec","errors/sec"),
    OutMulticastPktsRate("outMulticastPktsRate","kpps","kilo packets per sec"),
    OutOctetsRate("outOctetsRate","kbps","kilo bits per sec"),
    OutUcastPktsRate("outUcastPktsRate","kpps","kilo packets per sec"),
    SymbolErrorsRate("symbolErrorsRate","errors/sec","errors/sec");
    
    private String metricName;
    private String metricUnit;
    private String unitDescription;
    
    private CloudVisionMetricUnitEnum(String metricName, String metricUnit, String unitDescription){
	this.metricName = metricName;
	this.metricUnit = metricUnit;
	this.unitDescription = unitDescription;
    }

    public String getMetricName() {
        return metricName;
    }

    public String getMetricUnit() {
        return metricUnit;
    }

    public String getUnitDescription() {
        return unitDescription;
    }
    
    
}
