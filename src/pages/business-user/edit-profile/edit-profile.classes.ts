export class CountryCodes {
    _id: string;
    country_name: string;
    dial_code: string;
    code: string;
    currency_code: string;
}

export class EditProfile {
    firstname: string;
    lastname: string;
    email: string;
    phone_no: string;
    country: string;
    full_phone?: string;
    referralCode: string;
}