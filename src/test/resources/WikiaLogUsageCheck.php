<?php
class Foo {
    function bar() {
        Wikia::log(__METHOD__); // NOK

        Wikia::fooBar();

        WikiaLogger::instance()->debug();
    }
}
