// **********************************************************************
//
// Copyright (c) 2003-2015 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.6.1
//
// <auto-generated>
//
// Generated from file `HelloWorld.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package myHelloWorld;

public interface HelloWorldPrx extends Ice.ObjectPrx
{
    public void say(String s);

    public void say(String s, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_say(String s);

    public Ice.AsyncResult begin_say(String s, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_say(String s, Ice.Callback __cb);

    public Ice.AsyncResult begin_say(String s, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_say(String s, Callback_HelloWorld_say __cb);

    public Ice.AsyncResult begin_say(String s, java.util.Map<String, String> __ctx, Callback_HelloWorld_say __cb);

    public Ice.AsyncResult begin_say(String s, 
                                     IceInternal.Functional_VoidCallback __responseCb, 
                                     IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_say(String s, 
                                     IceInternal.Functional_VoidCallback __responseCb, 
                                     IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                     IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_say(String s, 
                                     java.util.Map<String, String> __ctx, 
                                     IceInternal.Functional_VoidCallback __responseCb, 
                                     IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_say(String s, 
                                     java.util.Map<String, String> __ctx, 
                                     IceInternal.Functional_VoidCallback __responseCb, 
                                     IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                     IceInternal.Functional_BoolCallback __sentCb);

    public void end_say(Ice.AsyncResult __result);
}
