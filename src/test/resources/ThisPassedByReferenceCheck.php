<?php
class Foo {
    private $property;

    public function bar() {
        $foo = 8;

        Hooks::run( [ &$this, $foo ] ); // NOK

        Hooks::run( [ $this, $foo ] ); // OK
        Hooks::run( [ &$this->property, $foo ] ); // OK
    }
}
