export class NearBy {
    longitude: number;
    latitude: number;
}

export class Location {
    type: string;
    coordinates: Array<number>;
}

export class CategoryInfo {
    category_bg_image: string;
    category_icon: string;
    category_name: string;
    created_at: string;
    is_active: boolean;
    _id: string;
}

export class BusinessInfo {
    constructor() {
        this.categoryInfo = new CategoryInfo();
        this.location = new Location();
    }

    award_dew_points: string;
    business_category: string;
    business_icon: string;
    business_logo: string;
    business_name: string;
    business_short_name: string;
    c_code_landline_no: string;
    c_code_phone_no: string;

    categoryInfo: CategoryInfo;

    country: string;
    created_at: string;
    dew_points: number;
    email: string;
    expire_at: string;
    first_address: string;
    first_name: string;
    free_plan: boolean;
    full_landline: string;
    full_phone: string;

    geo: Array<number>;

    isDeleted: boolean;
    is_active: boolean;
    is_approved: boolean;
    is_first_login: boolean;
    landline_no: string;
    last_address: string;
    last_name: string;
    latitude: string;

    location: Location;

    longitude: string;
    passwordHash: string;
    phone_no: string;
    plan_db_id: string;
    plan_purchased: boolean;
    postal_code: string;
    referral_by_code: string;
    referral_code: string;
    salt: string;
    spent_amount: string;
    updated_at: string;
    verification_key: string;
    _id: string;
}

export class NearByDetail {

    constructor() {
        this.location = new Location();
        this.businessInfo = new BusinessInfo();
    }

    branch_city: string;
    branch_country: string;
    branch_first_address: string;
    branch_last_address: string;
    branch_latitude: string;
    branch_longitude: string;
    branch_name: string;
    branch_phone_no: string;
    branch_postal_code: string;
    branch_state: string;
    branch_website_url: string;

    geo: Array<number>;
    businessInfo: BusinessInfo;

    business_id: string;
    c_code_phone_no: string;
    checkInButton: boolean;
    distanceFromYou: string;
    friday_close: boolean;
    friday_from: string;
    friday_to: string;

    location: Location;

    monday_close: string;
    monday_from: string;
    monday_to: string;
    saturday_close: string;
    saturday_from: string;
    saturday_to: string;
    sunday_close: boolean;
    sunday_from: string;
    sunday_to: string;
    thursday_close: string;
    thursday_from: string;
    thursday_to: string;
    tuesday_close: boolean;
    tuesday_from: string;
    tuesday_to: string;
    updated_at: string;
    wednesday_close: boolean;
    wednesday_from: string;
    wednesday_to: string;
    _id: string;
}