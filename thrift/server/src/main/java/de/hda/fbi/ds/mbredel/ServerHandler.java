/*
 Copyright (c) 2017, Michael Bredel, H-DA
 ALL RIGHTS RESERVED.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.

 Neither the name of the H-DA and Michael Bredel
 nor the names of its contributors may be used to endorse or promote
 products derived from this software without specific prior written
 permission.
 */

package de.hda.fbi.ds.mbredel;

import org.apache.thrift.TException;

/**
 * The server handler that implements the
 * Thrift interface methods and handles
 * the incoming RPC messages.
 */
public class ServerHandler implements Calc.Iface {
    @Override
    public void ping() throws TException {
        // Not implemented yet.
        throw new UnsupportedOperationException();
    }

    @Override
    public int addTwo(int num1, int num2) throws TException {
        System.out.println("Received: " + num1 + ", " + num2);
        return num1 + num2;
    }

    @Override
    public Result addOne(Operands operands) throws TException {
        return null;
    }
}
