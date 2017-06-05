#include <string.h>
#include <stdint.h>
#include <stddef.h>
#include <stdbool.h>
#include "pb_encode.h"
#include "pb_decode.h"
#include "message.pb.h"

/* This is the buffer where we will store our message. */

uint8_t buffer[520];

size_t message_length;

bool status;


void toProto(int x, int y, int z,int m){
  /* Encode our message */
    {
        /* Allocate space on the stack to store the message data.
         *
         * Nanopb generates simple struct definitions for all the messages.
         * - check out the contents of simple.pb.h!
         * It is a good idea to always initialize your structures
         * so that you do not have garbage data from RAM in there.
         */

        proto_espData message = proto_espData_init_zero;
        /* Create a stream that will write to our buffer. */
        pb_ostream_t stream = pb_ostream_from_buffer(buffer, sizeof(buffer));

        /* Fill in the lucky number */
        message.x = x;
        message.y =y;
        message.z =z;
        message.m = m;

        /* Now we are ready to encode the message! */
        status = pb_encode(&stream, proto_espData_fields, &message);
        message_length = stream.bytes_written;

        /* Then just check for any errors.. */
//         if (!status)
//         {
//             Serial.println("Encoding failed: %s\n", PB_GET_ERROR(&stream));
//         }
    }

}



void setup()
{
  Serial.begin(9600);
  

}

void loop()
{
  toProto(10, 12, 14, 16);

}

